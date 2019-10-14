package springboot2.SpringBoot2.Service;

import springboot2.SpringBoot2.Entity.Syllabus;
import springboot2.SpringBoot2.Entity.User;

import java.util.List;

public interface SyllabusService {
    public Syllabus findSyllabusById(int id);
    public void save(Syllabus syllabus);
}
