package springboot2.SpringBoot2.Entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="springusers")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="first_name")
    private String name;
    @Column(name="last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    public User(String name, String lastName, String password)
    {
        this.name = name;
        this.lastName = lastName;
        this.password = password;
    }
    public User()
    {
        name= "";
        lastName = "";
        password ="";
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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


}

