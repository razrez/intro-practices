using HeroesGraphQLI.Data;
using HeroesGraphQLI.Interfaces;

namespace HeroesGraphQLI.Repositories;

public class SuperpowerRepository : ISuperpowerRepository
{
    private readonly ApplicationDbContext _appDbContext;

    public SuperpowerRepository(ApplicationDbContext appDbContext)
    {
        _appDbContext = appDbContext;
    }
}