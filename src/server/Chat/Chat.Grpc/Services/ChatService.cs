using Chat.Grpc.Models;
using Google.Protobuf;
using Grpc.Core;
using Microsoft.AspNetCore.Authorization;

namespace Chat.Grpc.Services;

[Authorize]
public class ChatService : Chat.ChatBase
{
    private readonly ILogger<ChatService> _logger;
    private readonly ChatRoom _chatroomService;

    public ChatService(ILogger<ChatService> logger, ChatRoom chatRoomService)
    {
        _logger = logger;
        _chatroomService = chatRoomService;
    }

    // Returns jwt token if it doesn't exist
    public override Task<AuthorizeResponse> Authorize(User request, ServerCallContext context)
    {
        return Task.FromResult(new AuthorizeResponse
        {
            StatusCode = 200,
            Info = "succeed",
            Token = "token"
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
}