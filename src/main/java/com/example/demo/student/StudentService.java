package com.example.demo.student;

import com.example.demo.student.models.Student;
import com.example.demo.student.models.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    String success_response = "{" + "status:200" +
            "}";

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();

    }


    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(
                    "Student with id " + studentId + " doesn't exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public StudentResponse updateStudent(Long studentId, String name, String email,String dob) {


        StudentResponse response = new StudentResponse();
        String errorMsg = "";
        String successMsg = "";

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
                "student with id " + studentId + " doesn't exists"
        ));

        try {
            if (name != null && name.length() > 0 && !(name.equals(student.getName()))) {
                student.setName(name);

            }

            if(dob == ""){
                errorMsg = "wrong format";
                throw new DataFormatException("wrong format");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
            LocalDate dobFormatted = LocalDate.parse(dob.toUpperCase(), formatter);
            System.out.println(dobFormatted);

            System.out.println("ผ่าน 5");


            if (email != null && email.length() > 0 && !(email.equals(student.getEmail()))) {
                Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
                if (studentOptional.isPresent()) {
                    errorMsg = "email taken";
                    throw new IllegalStateException("email has taken");


                }
                student.setEmail(email);
            }

            System.out.println("ผ่าน 6");



            successMsg = "data has submit";
            response.setMessage("Student updated success \n,Message: " + successMsg + "!");
            response.setStatus(HttpStatus.OK.value());

        } catch (Exception e) {
            response.setMessage("Student updated failure \n,Message: " + errorMsg+e.toString() + ", \nPlease try again!");
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }


        return response;
    }
}
