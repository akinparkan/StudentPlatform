package springboot2.SpringBoot2.Entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="instructors")
public class Instructor implements UserInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="name")
    private String name;


    @Column(name="last_name")
    private String lastName;


    @Column(name="password")
    private String password;


    @OneToMany(mappedBy = "instructor")
    private List<Lesson> lessons;


    @Column(name="dept")
    private String dept;


    @Column(name="email")
    private String email;

    //we need constructors
    @Autowired
    public Instructor(String name, String lastName,String password, String dept, String email)
    {
        this.dept=dept;
        this.email = email;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
        lessons = new ArrayList<Lesson>();
    }
    public Instructor()
    {
        this.dept="";
        this.email = "";
        this.lastName = "";
        this.name = "";
        this.password = "";
        lessons = new ArrayList<Lesson>();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void  addLesson(Lesson lesson)
    {
        if(lessons == null)
            lessons = new ArrayList<Lesson>();
        lessons.add(lesson);
        lesson.setInstructor(this);
    }
}
