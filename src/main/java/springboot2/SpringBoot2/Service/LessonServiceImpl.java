package springboot2.SpringBoot2.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot2.SpringBoot2.Dao.LessonRepo;
import springboot2.SpringBoot2.Entity.Lesson;

import java.util.List;
import java.util.Optional;
@Service
public class LessonServiceImpl implements LessonService {


    private LessonRepo repository;
    @Autowired
    public LessonServiceImpl(LessonRepo repository) {
        this.repository = repository;
    }

    @Override
    public Lesson findLessonById(int id) {
        Optional<Lesson> opt= repository.findById(id);
        Lesson result;
        if(opt.isPresent())
            result = opt.get();
        else
        {
            throw new RuntimeException("Did not find employee id - " + id);
        }
        return result;
    }

    @Override
    public List<Lesson> getAllLessons() {
        return repository.findAllByOrderByLessonNameAsc();
    }

    @Override
    public void save(Lesson lesson) {
        repository.save(lesson);
    }

    @Override
    public Lesson findLessonByName(String lessonName)
    {
       return repository.findByLessonName(lessonName);
    }


}
