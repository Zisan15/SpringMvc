package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public void saveStudent(Student student) {
        repository.save(student);

    }

    @Override
    public List<Student> getAll() {
//        List<Student> studentList = repository.findAll();
//        return studentList;
        return repository.findAll();//iki satırlık işlem yerine bunu yaptık
    }

    @Override
    public Student getStudentById(Long id) {

        Student student = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("student not found by id: " + id));
//get();-> No such element ex.
        return student;
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        repository.delete(student.getId());
    }
}
