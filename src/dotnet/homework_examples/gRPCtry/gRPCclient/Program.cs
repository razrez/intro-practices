using System.Threading.Tasks;
using Grpc.Net.Client;
using gRPCtryClient;

// The port number must match the port of the gRPC server.
using var channel = GrpcChannel.ForAddress("http://localhost:5167");
var client = new Calculator.CalculatorClient(channel);
var reply = await client.CalculateAsync(
    new CalculateRequest()
    {
        Arg1 = 1.5,
        Operation = "+",
        Arg2 = 227
    });
Console.WriteLine("Calculation result: " + reply.Result);
Console.WriteLine("Press any key to exit...");
Console.ReadKey();