using ClickHouse.Client.ADO;
using ClickHouse.Client.Utility;
using ClickHouseIntro.Services;
using Microsoft.AspNetCore.Mvc;

namespace ClickHouseIntro.Controllers;

[ApiController]
[Route("[controller]")]
public class WeatherForecastController : ControllerBase
{
    private readonly IClickHouseService _clickHouseService;

    private readonly ILogger<WeatherForecastController> _logger;

    public WeatherForecastController(ILogger<WeatherForecastController> logger, IClickHouseService clickHouseService)
    {
        _logger = logger;
        _clickHouseService = clickHouseService;
    }
    
    [Route("api/table")]
    [HttpPost]
    public async Task<IActionResult> CreateTable(string tableName)
    {
        await _clickHouseService.CreateTableByName(tableName);
        return Ok($"{tableName} created");
    }
    
    [Route("api/table")]
    [HttpDelete]
    public async Task<IActionResult> DeleteTable(string tableName)
    {
        await _clickHouseService.DropTableByName(tableName);
        return Ok($"{tableName} dropped");
    }
    
    [Route("api/value")]
    [HttpPost]
    public async Task<IActionResult> InsetValue(string tableName, int value)
    {
        await _clickHouseService.InsertValue(tableName, value);
        return Ok("inserted");
    }
    
    [Route("api/value")]
    [HttpDelete]
    public async Task<IActionResult> DeleteValue(string tableName, int value)
    {
        await _clickHouseService.DeleteValue(tableName, value);
        return Ok("removed");
    }
    
    [Route("api/between")]
    [HttpGet]
    public async Task<IActionResult> SelectBetween(string tableName, int left, int right)
    {
        var res = await _clickHouseService.SelectBetween(tableName, left, right);
        return Ok(res);
    }
}