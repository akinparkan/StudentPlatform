package springboot2.SpringBoot2.Service;

import springboot2.SpringBoot2.Entity.User;

import java.util.List;

public interface UserService {
    public User findUserById(long id);
    public List<User> getAllUser();
    public void save(User user);
}
