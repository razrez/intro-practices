using System.Collections.Concurrent;
using Chat.Grpc.Models;
using Chat.Grpc.Services;
using Chat.Infrastructure.Persistence;

var builder = WebApplication.CreateBuilder(args);

// Additional configuration is required to successfully run gRPC on macOS.
// For instructions on how to configure Kestrel and gRPC clients on macOS, visit https://go.microsoft.com/fwlink/?linkid=2099682

// Add services to the container.
builder.Services.AddInfrastructure(builder.Configuration);
builder.Services.AddSingleton<ChatRoom>();
builder.Services.AddGrpc();

var app = builder.Build();

// Configure the HTTP request pipeline. 
app.MapGrpcService<ChatService>();
app.MapGet("/",
    () =>
        "Communication with gRPC endpoints must be made through a gRPC client. To learn how to create a client, visit: https://go.microsoft.com/fwlink/?linkid=2086909");
app.Run();