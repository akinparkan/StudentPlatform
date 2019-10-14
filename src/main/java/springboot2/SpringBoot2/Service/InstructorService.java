package springboot2.SpringBoot2.Service;

import springboot2.SpringBoot2.Entity.Instructor;
import springboot2.SpringBoot2.Entity.User;

import java.util.List;

public interface InstructorService {
    public Instructor findInstructorById(int id);
    public List<Instructor> getAllInstructor();
    public void save(Instructor instructor);
    public Instructor findByInsName(String name);
}
