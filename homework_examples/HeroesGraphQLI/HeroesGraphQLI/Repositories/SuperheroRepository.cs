using HeroesGraphQLI.Data;
using HeroesGraphQLI.Interfaces;

namespace HeroesGraphQLI.Repositories;

public class SuperheroRepository : ISuperheroRepository
{
    private readonly ApplicationDbContext _appDbContext;

    public SuperheroRepository(ApplicationDbContext appDbContext)
    {
        _appDbContext = appDbContext;
    }
}