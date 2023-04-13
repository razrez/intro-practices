using Microsoft.AspNetCore.Mvc;

namespace SerilogIntro.Controllers;

[ApiController]
[Route("[controller]")]
public class WeatherForecastController : ControllerBase
{
    private readonly ILogger<WeatherForecastController> _logger;

    public WeatherForecastController(ILogger<WeatherForecastController> logger)
    {
        _logger = logger;
    }

    [HttpGet(Name = "TestLogging")]
    public IActionResult TestLog(string command)
    {
        if (command == "ok")
        {
            _logger.LogInformation("SUCCESFUL COMMAND :)");
            return Ok();
        }

        _logger.LogInformation("BAD COMMAND!");
        return BadRequest();
    }
    
}