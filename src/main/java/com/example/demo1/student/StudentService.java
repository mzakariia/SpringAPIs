package com.example.demo1.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final  StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("student with id "+studentId+" does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long id,String name, String email){
        Student studentRecord = studentRepository.findById(id)
                .orElseThrow(
                        ()-> new IllegalStateException("student with id "+id+" does not exist")
                );
        studentRecord.setName(name);
        studentRecord.setEmail(email);
    }
}
