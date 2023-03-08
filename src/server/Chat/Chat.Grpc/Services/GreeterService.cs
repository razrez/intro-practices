using Grpc.Core;
using Chat.Grpc;

namespace Chat.Grpc.Services;

public class ChatService : Chat.ChatBase
{
    private readonly ILogger<ChatService> _logger;

    public ChatService(ILogger<ChatService> logger)
    {
        _logger = logger;
    }

    public override Task<MessageReply> SayHello(MessageRequest request, ServerCallContext context)
    {
        return Task.FromResult(new MessageReply
        {
            Message = request.Message
        });
    }
}