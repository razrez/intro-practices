namespace gRPCtry;

public class DumbCalculator
{
    public static double DoCalculate(double val1, string operation, double val2)
    {
        double result = 0;
        result = operation switch
        {
            "+" => val1 + val2,
            "-" => val1 - val2,
            "*" => val1 * val2,
            "/" => val1 / val2,
            _ => result
        };
        return result;
    }
}
