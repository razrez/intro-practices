using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using Chat.Grpc.Models;
using Google.Protobuf;
using Grpc.Core;
using Microsoft.AspNetCore.Authorization;
using Microsoft.IdentityModel.Tokens;

namespace Chat.Grpc.Services;

[Authorize]
public class ChatService : Chat.ChatBase
{
    private readonly ILogger<ChatService> _logger;
    private readonly ChatRoom _chatroomService;
    private readonly IConfiguration _configuration;

    public ChatService(ILogger<ChatService> logger, ChatRoom chatRoomService, IConfiguration configuration)
    {
        _logger = logger;
        _chatroomService = chatRoomService;
        _configuration = configuration;
    }

    // Returns jwt token if it doesn't exist
    public override Task<AuthorizeResponse> Authorize(User request, ServerCallContext context)
    {
        var authClaims = new List<Claim>
        {
            new Claim(ClaimTypes.Name, request.Name),
            new Claim(JwtRegisteredClaimNames.Jti, Guid.NewGuid().ToString())
        };
        
        var token = GetToken(authClaims);
        
        return Task.FromResult(new AuthorizeResponse
        {
            Token = new JwtSecurityTokenHandler().WriteToken(token)
        });
    }

    [Authorize]
    public override async Task ChatMessaging(IAsyncStreamReader<Message> requestStream, IServerStreamWriter<Message> responseStream, ServerCallContext context)
    {
        if (!await requestStream.MoveNext()) return;
        
        do
        {
            _chatroomService.Join(requestStream.Current.User, responseStream);
            await _chatroomService.BroadcastMessageAsync(requestStream.Current);
        } 
        while (await requestStream.MoveNext() && !context.CancellationToken.IsCancellationRequested);

        _chatroomService.Remove(context.Peer);
    }
    
    private JwtSecurityToken GetToken(List<Claim> authClaims)
    {
        var authSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_configuration["JWT:Secret"]));

        var token = new JwtSecurityToken(
            issuer: _configuration["JWT:ValidIssuer"],
            audience: _configuration["JWT:ValidAudience"],
            expires: DateTime.Now.AddDays(120),
            claims: authClaims,
            signingCredentials: new SigningCredentials(authSigningKey, SecurityAlgorithms.HmacSha256)
        );

        return token;
    }
}