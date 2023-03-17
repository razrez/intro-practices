using Grpc.Core;
using gRPCtry;

namespace gRPCtry.Services;

public class CalculatorService : Calculator.CalculatorBase
{
    private readonly ILogger<CalculatorService> _logger;

    public CalculatorService(ILogger<CalculatorService> logger)
    {
        _logger = logger;
    }

    public override Task<CalculateReply> Calculate(CalculateRequest request, ServerCallContext context)
    {
        return Task.FromResult(new CalculateReply
        {
            Result = DumbCalculator.DoCalculate(request.Arg1, request.Operation, request.Arg2)
        });
    }
}