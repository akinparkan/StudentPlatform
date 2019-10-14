package springboot2.SpringBoot2.Service;

import springboot2.SpringBoot2.Entity.Student;
import springboot2.SpringBoot2.Entity.User;

import java.util.List;

public interface StudentService {
    public Student findStudentById(int id);
    public List<Student> getAllStudent();
    public Student findStudentByEmail(String email);
    public void save(Student student);
}
