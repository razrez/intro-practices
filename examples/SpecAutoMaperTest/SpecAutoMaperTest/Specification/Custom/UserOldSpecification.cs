using SpecAutoMaperTest.Controllers;

namespace SpecAutoMaperTest.Specification.Custom;

public class UserOldSpecification : BaseSpecifcation<User>
{
    public UserOldSpecification(int acceptAge) : base(x => x.Age >= acceptAge)
    {
        AddInclude(x => x.Name);
    }
}