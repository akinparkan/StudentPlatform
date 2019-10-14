package springboot2.SpringBoot2.Service;

import springboot2.SpringBoot2.Entity.Lesson;
import springboot2.SpringBoot2.Entity.User;

import java.util.List;

public interface LessonService {
    public Lesson findLessonById(int id);
    public List<Lesson> getAllLessons();
    public void save(Lesson lesson);
    public Lesson findLessonByName(String lessonName);
}
