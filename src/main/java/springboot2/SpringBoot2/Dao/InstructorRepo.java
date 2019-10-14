package springboot2.SpringBoot2.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot2.SpringBoot2.Entity.Instructor;

import java.util.List;
@Repository
public interface InstructorRepo extends JpaRepository<Instructor, Integer> {
    List<Instructor> findAllByOrderByLastNameAsc();
    Instructor findByEmail(String email);
    Instructor findByName(String name);
}
