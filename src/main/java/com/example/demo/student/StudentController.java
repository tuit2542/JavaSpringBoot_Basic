package com.example.demo.student;

import com.example.demo.student.models.Student;
import com.example.demo.student.models.StudentResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "v1/api")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/getAllStudent")
    public List<Student> getAllStudent() {
        return studentService.getStudents();
    }

    @PostMapping(path = "/addStudent")
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "/deleteStudent/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "/updateStudent/{studentId}")
    @ResponseBody
    public StudentResponse updateStudent(@PathVariable("studentId") Long studentId,
//            @RequestParam(required = false, value = "name") String name,
//            @RequestParam(required = false, value = "email") String email,
                                                         @RequestBody Map<String, Object> requestBody) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String requestBodyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestBody);


        String name = (String) requestBody.get("name");
        String email = (String) requestBody.get("email");

        System.out.println("requestBody: "+requestBodyJson);

        System.out.println(requestBody);



       StudentResponse response = studentService.updateStudent(studentId, name, email);
       return  response;
    }

}
