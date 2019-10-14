package springboot2.SpringBoot2.Service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import springboot2.SpringBoot2.Dao.StudentRepo;
import springboot2.SpringBoot2.Entity.Student;

import javax.persistence.Table;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StudentServiceImplTest {


    @Test
    public void findStudentByEmail() {
        StudentRepo studentRepo = mock(StudentRepo.class);
        Mockito.when(studentRepo.findByEmail("akin.parkan@ug.bilkent.edu.tr"))
                .thenReturn(new Student("asd", "", null, null, "akin.parkan@ug.bilkent.edu.tr"));

        Student student = new StudentServiceImpl(studentRepo)
                .findStudentByEmail("akin.parkan@ug.bilkent.edu.tr");

        Assertions.assertThat(student
                .getEmail())
                .isEqualTo("akin.parkan@ug.bilkent.edu.tr");
    }

    @Test
    public void testMultiply() {
        Assertions.assertThat(new StudentServiceImpl(null)
                .multiply(2, 3))
                .isEqualTo(6);

        Assertions.assertThat(new StudentServiceImpl(null)
                .multiply(9, 3))
                .isEqualTo(27);
    }
    @Test
    public void testFindStudentByID()
    {
        // verilen id ile dao call yapıldı mı ( Mockito.verify() ) (?)
        // eger db'de olan bir id gelmisse o id'li student'ı dön (OK)
        // eğer yanlış bir id gönderilirse RuntimeException fırlatıyormu (        Assertions.assertThatThrownBy() (OK)

        StudentRepo repo = mock(StudentRepo.class);
       StudentServiceImpl impl = mock(StudentServiceImpl.class);

        Mockito.when(repo.findByEmail("akin.parkan@ug.bilkent.edu.tr"))
                .thenReturn(new Student("asd", "parkan", null, null, "akin.parkan@ug.bilkent.edu.tr"));

        //StudentServiceImpl impl = new StudentServiceImpl(repo);

        Mockito.when(impl.findStudentById(100))
                .thenReturn(new Student("abc","def",null,null,"asd"));
       // Mockito.when(repo.findById(100))
         //       .thenReturn(Optional<Student>(new Student("asd", "parkan", null, null, "akin.parkan@ug.bilkent.edu.tr")));
        //Assertions.assertThat(new StudentServiceImpl(repo));
      //  impl.setRepository(repo);
//
        Assertions.assertThat(impl
                .findStudentById(100)
                .getLastName())
                .isEqualTo("def");

        Mockito.verify(impl).findStudentById(100);

        Assertions.assertThatThrownBy(()->{
            impl.findStudentById(10);
        }).isInstanceOf(RuntimeException.class);

    }
}