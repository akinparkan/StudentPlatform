package springboot2.SpringBoot2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot2.SpringBoot2.Dao.UserRepo;
import springboot2.SpringBoot2.Entity.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo repository;
    @Autowired
    public UserServiceImpl(UserRepo theRepository)
    {
        repository = theRepository;
    }

    @Override
    public User findUserById(long id) {
        Optional<User> opt= repository.findById(id);
        User result;
        if(opt.isPresent())
            result = opt.get();
        else
        {
            throw new RuntimeException("Did not find employee id - " + id);
        }
        return result;
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAllByOrderByLastNameAsc();
    }

    @Override
    public void save(User user) {
        repository.save(user);
    }

}
