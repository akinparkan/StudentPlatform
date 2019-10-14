package springboot2.SpringBoot2.Entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name="syllabus")
public class Syllabus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="syllabus_detail")
    private String syllabusDetail;

    @Autowired
    public Syllabus(String syllabusDetail)
    {
        this.syllabusDetail = syllabusDetail;
    }

    public Syllabus()
    {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSyllabusDetail() {
        return syllabusDetail;
    }

    public void setSyllabusDetail(String syllabusDetail) {
        this.syllabusDetail = syllabusDetail;
    }
}
