package com.example.demo.student;

import com.example.demo.student.models.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            List<Student> listDummy = new ArrayList<>();
            for (int i = 1  ; i <= 20 ; i ++){
                int year = (int) 1999 - i;
                listDummy.add(new Student((long) i,
                        "dummy"+i,
                        "dummy"+i+"@gmail.com",
                        LocalDate.of(year , Month.MAY, i)
                ));

            }
            repository.saveAll(listDummy);
        };
    }
}
