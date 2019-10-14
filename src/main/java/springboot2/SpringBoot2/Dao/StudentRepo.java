package springboot2.SpringBoot2.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot2.SpringBoot2.Entity.Student;

import java.util.List;
@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    List<Student> findAllByOrderByLastNameAsc();
    Student findByEmail(String email);
}

