using AutoMapper;
using SpecAutoMaperTest.Controllers;
using SpecAutoMaperTest.Dto;

namespace SpecAutoMaperTest.Mappers;

public class MappingProfile : Profile {
    public MappingProfile() {
        // Add as many of these lines as you need to map your objects
        CreateMap<User, UserDto>()
            .ForMember(m => m.Name, opt=> opt.MapFrom(src => src.Name))
            .ForMember(m => m.Age, opt=> opt.MapFrom(src => src.Age));
        /*CreateMap<UserDto, User>();
        CreateMap<IQueryable<UserDto>, IQueryable<User>>();
        CreateMap<IQueryable<User>, IQueryable<UserDto>>();*/
    }
}