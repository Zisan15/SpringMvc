package com.tpe.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Entity
@Table(name = "t_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "please provide valid first name")//first name empty("") olamaz, null değer alamaz, blank(space/boşluk) olamaz
    private String firstName;

    @NotEmpty(message = "please provide valid last name")//empty, null kabul etmez ancak blank kabul ediyor
    private String lastName;

    @NotNull(message = "please provide valid grade")//sadece null olamaz
    private Integer grade;

    private LocalDateTime creatDate = LocalDateTime.now();

    //getter - setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public LocalDateTime getCreatDate() {
        return creatDate;
    }


    //toString
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", grade=" + grade +
                ", creatDate=" + creatDate +
                '}';
    }
}
