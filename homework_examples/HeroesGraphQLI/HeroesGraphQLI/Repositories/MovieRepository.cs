using HeroesGraphQLI.Data;
using HeroesGraphQLI.Interfaces;

namespace HeroesGraphQLI.Repositories;

public class MovieRepository : IMovieRepository
{
    private readonly ApplicationDbContext _appDbContext;

    public MovieRepository(ApplicationDbContext appDbContext)
    {
        _appDbContext = appDbContext;
    }
}