package com.example.Student.studentinfo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
     @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
         return studentRepository.findAll();
      /*  return List.of(
                new Student(1L,
                "Shubhangi",
                "Shubhangishibu@gmail.com",
                LocalDate.of(2000, Month.OCTOBER, 6),
                21)
        );*/
    }

    public void registerNewStudent(Student student) {
        Optional<Student> studentOptional=
         studentRepository.findStudentByEmail(student.getEmail());
         if(studentOptional.isPresent())
         {
             throw new IllegalStateException("Email is already taken");

         }
         studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean s= studentRepository.existsById(studentId);
       if(s)  studentRepository.deleteById(studentId);
       else
       throw new IllegalStateException("Invalid Student ID");
    }
@Transactional
    public void updateStudent(Long studentId, String name, String email) {
    Optional<Student> s= studentRepository.findById(studentId);
    if(!(s.isPresent())) throw new IllegalStateException("Invalid studentId entered");
    Optional<Student> studentByEmail=
            studentRepository.findStudentByEmail(email);
    if(studentByEmail.isPresent())
    {
        throw new IllegalStateException("Email is taken, please retry!");
    }
    if(email!=null &&email.length()>0)
    s.get().setEmail(email);
    if(name!=null&&name.length()>0 )
    s.get().setName(name);
    if(name!=null && name.length()<1)
        throw new IllegalStateException("Please enter valid name");


     }
}
