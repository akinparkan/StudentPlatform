package springboot2.SpringBoot2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot2.SpringBoot2.Dao.SyllabusRepo;
import springboot2.SpringBoot2.Entity.Syllabus;

import java.util.List;
import java.util.Optional;
@Service
public class SyllabusServiceImpl implements SyllabusService {


    private SyllabusRepo repository;

    @Autowired
    public SyllabusServiceImpl(SyllabusRepo repository) {
        this.repository = repository;
    }

    @Override
    public Syllabus findSyllabusById(int id) {
        Optional<Syllabus> opt= repository.findById(id);
        Syllabus result;
        if(opt.isPresent())
            result = opt.get();
        else
        {
            throw new RuntimeException("Did not find employee id - " + id);
        }
        return result;
    }

    @Override
    public void save(Syllabus syllabus) {
        repository.save(syllabus);

    }
}
