package springboot2.SpringBoot2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot2.SpringBoot2.Dao.InstructorRepo;
import springboot2.SpringBoot2.Entity.Instructor;

import java.util.List;
import java.util.Optional;
@Service
public class InstructorServiceImpl implements InstructorService {

    private InstructorRepo repository;
    @Autowired
    public InstructorServiceImpl(InstructorRepo repository) {
        this.repository = repository;
    }

    @Override
    public Instructor findInstructorById(int id) {
        Optional<Instructor> opt= repository.findById(id);
        Instructor result;
        if(opt.isPresent())
            result = opt.get();
        else
        {
            throw new RuntimeException("Did not find employee id - " + id);
        }
        return result;
    }

    @Override
    public List<Instructor> getAllInstructor() {
        return repository.findAllByOrderByLastNameAsc();
    }

    @Override
    public void save(Instructor instructor) {
        repository.save(instructor);
    }
    @Override
    public Instructor findByInsName(String name)
    {
        return repository.findByName(name);
    }
}
