using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using SpecAutoMaperTest.Dto;
using SpecAutoMaperTest.Specification.Custom;

namespace SpecAutoMaperTest.Controllers;

[ApiController]
[Route("api/users")]
[Produces("application/json")]
public class UserController : Controller
{
    private readonly IQueryable <User> _users;
    private readonly IMapper _mapper;
    public UserController(IMapper mapper)
    {
        _mapper = mapper;
        _users = new List<User>()
        {
            new User { Id = 1, Name = "rus", Surname = "lan", Age = 9 },
            new User { Id = 2, Name = "ded", Surname = "ik", Age = 19 },
            new User { Id = 3, Name = "ran", Surname = "mdas", Age = 3 },
            new User { Id = 4, Name = "russss", Surname = "sadas", Age = 23 },
            new User { Id = 5, Name = "rusaa", Surname = "asd", Age = 11 },
            new User { Id = 6, Name = "sir", Surname = "ds", Age = 40 },
            new User{ Id = 7, Name = "old", Surname = "sdaas", Age = 16 },
        }.AsQueryable();
    }

    [HttpGet]
    public IActionResult GetOldUsers(int ageLimit)
    {
        var specOld = new UserOldSpecification(ageLimit).Criteria;
        
        var oldsUsers = _users.Where(specOld).Select(r => _mapper.Map<UserDto>(r));
        
        
        return Ok(oldsUsers);
    }
}