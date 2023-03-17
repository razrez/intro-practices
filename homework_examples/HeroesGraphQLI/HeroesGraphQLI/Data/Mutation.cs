using HeroesGraphQLI.Models;
using Microsoft.EntityFrameworkCore.ChangeTracking;

namespace HeroesGraphQLI.Data;

public class Mutation
{
    private readonly ApplicationDbContext _context;
    
    public Mutation(ApplicationDbContext applicationDbContext)
    {
        _context = applicationDbContext;
    }
    
    [UseMutationConvention]
    public async Task<Superhero?> AddHero([ID] Guid id, string name, string description, double height)
    {
        var hero = await _context.Superheroes.AddAsync(new Superhero
        {
            Id = id,
            Name = name,
            Description = description,
            Height = height
        });
        await _context.SaveChangesAsync();
        
        return await _context.Superheroes.FindAsync(id);
    }
}