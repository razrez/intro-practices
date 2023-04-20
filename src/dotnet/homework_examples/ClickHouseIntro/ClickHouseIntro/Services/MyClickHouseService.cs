using System.Text.Json;
using ClickHouse.Client.ADO;
using ClickHouse.Client.Utility;

namespace ClickHouseIntro.Services;

public class MyClickHouseService : IClickHouseService
{
    private const string ConnectionString = "Host=localhost;Protocol=http;Port=8123";
    private readonly ClickHouseConnection _connection;

    public MyClickHouseService()
    {
        _connection = new ClickHouseConnection(ConnectionString);
    }
    
    public async Task CreateTableByName(string tableName)
    {
        var command = 
                $"CREATE TABLE {tableName} (x Int64) ENGINE = Memory AS SELECT 1";
        
        await _connection.ExecuteScalarAsync(command);
    }
    
    public async Task DropTableByName(string tableName)
    {
        var command = 
            $"DROP TABLE IF EXISTS {tableName};";
        
        await _connection.ExecuteScalarAsync(command);
    }
    
    public async Task InsertValue(string tableName, int value)
    {
        var command = 
            $"INSERT INTO {tableName} VALUES ({value})";
        
        await _connection.ExecuteScalarAsync(command);
    }
    
    public async Task DeleteValue(string tableName, int value)
    {
        // x - the only one attribute in tables
        var command = 
            $"ALTER TABLE {tableName} DELETE WHERE x == {value}";
        
        await _connection.ExecuteScalarAsync(command);
    }
    
    public async Task<string?> SelectBetween(string tableName, int left, int right)
    {
        // x - the only one attribute in tables
        var command = 
            $"SELECT x FROM {tableName} where x > {left} and x < {right}";
        
        var res = await _connection.ExecuteScalarAsync(command);
        return res.ToString();
    }
    public async Task<string?> SelectOr(string tableName, int left, int right)
    {
        // x - the only one attribute in tables
        var command = 
            $"SELECT x FROM {tableName} where x > {left} or x < {right}";
        
        var res = await _connection.ExecuteScalarAsync(command);
        return res.ToString();
    }
    
    
}