package springboot2.SpringBoot2.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot2.SpringBoot2.Entity.User;

import java.util.List;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
   // User findByUserIDAndPassword(String username, String password);
    List<User> findAllByOrderByLastNameAsc();
}
