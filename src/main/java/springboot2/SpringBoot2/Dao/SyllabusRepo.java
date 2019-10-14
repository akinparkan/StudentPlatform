package springboot2.SpringBoot2.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot2.SpringBoot2.Entity.Syllabus;

import java.util.List;

@Repository
public interface SyllabusRepo extends JpaRepository<Syllabus, Integer> {
}

