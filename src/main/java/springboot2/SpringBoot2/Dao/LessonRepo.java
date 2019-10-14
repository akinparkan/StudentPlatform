package springboot2.SpringBoot2.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot2.SpringBoot2.Entity.Lesson;

import java.util.List;

@Repository
public interface LessonRepo extends JpaRepository<Lesson, Integer> {
    List<Lesson> findAllByOrderByLessonNameAsc();
    Lesson findByLessonName(String lessonName);
}
