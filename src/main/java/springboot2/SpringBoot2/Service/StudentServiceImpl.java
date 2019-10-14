package springboot2.SpringBoot2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import springboot2.SpringBoot2.Dao.StudentRepo;
import springboot2.SpringBoot2.Entity.Student;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepo repository;

    //use here
    @Autowired
    public StudentServiceImpl(StudentRepo repository) {
        this.repository = repository;
    }

    // verilen id ile dao call yapıldı mı ( Mockito.verify() )
    // eger db'de olan bir id gelmisse o id'li student'ı dön
    // eğer yanlış bir id gönderilirse RuntimeException fırlatıyormu (        Assertions.assertThatThrownBy()
    @Override
    public Student findStudentById(int id) {
        Optional<Student> opt= repository.findById(id);
        Student result;
        if(opt.isPresent())
            result = opt.get();
        else
        {
            throw new RuntimeException("Did not find employee id - " + id);
        }
        return result;
    }

    // bu method dao'nun o methodunu cagiriyor mu
    @Override
    public List<Student> getAllStudent() {
        return repository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Student findStudentByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void save(Student student) {
        repository.save(student);
    }

    public int multiply(int first, int second) {
        return first * second;
    }

    public void setRepository(StudentRepo repository)
    {
        repository=repository;
    }
}
