package springboot2.SpringBoot2.Entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="lessons")
public class Lesson  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="starting_time")
    private String startingTime;

    @Column(name="ending_time")
    private String endingTime;

    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    /*@JoinTable(
            name="lesson_student",
            joinColumns=@JoinColumn(name="lessons_id",referencedColumnName = "id"),
            inverseJoinColumns=@JoinColumn(name="students_id", referencedColumnName = "id")
    )*/
    @JoinTable(
            name="lesson_student",
            joinColumns= {@JoinColumn(name="lessons_id", referencedColumnName = "id")},
            inverseJoinColumns= {@JoinColumn(name="students_id", referencedColumnName = "id")}
    )
    private List<Student> students;

    @Column(name="lesson_name")
    private String lessonName;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="syllabus_id")
    private Syllabus syllabus;

    @Column(name="lesson_gpa")
    private double lessonGpa;

    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor instructor;


    public Lesson(String startingTime, String endingTime, String lessonName, int syllabusId,  double lessonGpa)
    {
        startingTime = startingTime;
        endingTime =endingTime;
        lessonName = lessonName;
        syllabusId = syllabusId;
        lessonGpa = lessonGpa;
        students = new ArrayList<Student>();
    }
    @Autowired
    public Lesson()
    {
        students = new ArrayList<Student>();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public double getLessonGpa() {
        return lessonGpa;
    }

    public void setLessonGpa(double lessonGpa) {
        this.lessonGpa = lessonGpa;
    }

    public void addStudent(Student student)
    {
        if(students == null) {
            students = new ArrayList<Student>();
        }

        students.add(student);
       // student.addLesson(this);
    }
    public void setSyllabus(Syllabus syllabus)
    {
        this.syllabus = syllabus;
    }
    public Syllabus getSyllabus()
    {
        return syllabus;
    }

}
