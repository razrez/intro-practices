namespace ClickHouseIntro.Services;

public interface IClickHouseService
{
    /// <summary>
    /// creates simple table with 1 attribute which type is Int64
    /// </summary>
    /// <param name="tableName"></param>
    /// <returns></returns>
    public Task CreateTableByName(string tableName);
    public Task DropTableByName(string tableName);
    public Task InsertValue(string tableName, int value);
    public Task DeleteValue(string tableName, int value);
    public Task<string?> SelectBetween(string tableName, int left, int right);

}