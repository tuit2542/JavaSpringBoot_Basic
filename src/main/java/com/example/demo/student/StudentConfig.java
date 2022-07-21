package com.example.demo.student;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
                Student dummy1 = new Student(
                    1L,
                    "dummy1",
                    "dummy1@gmail.com",
                    LocalDate.of(1999, Month.MAY,4)
                );

                Student dummy2 = new Student(
                    2L,
                    "dummy2",
                    "dummy2@gmail.com",
                    LocalDate.of(1999, Month.OCTOBER,20)
                 );

                Student dummy3 = new Student(
                    3L,
                    "dummy3",
                    "dummy3@gmail.com",
                    LocalDate.of(1999, Month.DECEMBER,17)
                );

                repository.saveAll(List.of(dummy1,dummy2,dummy3));
        };
    }
}
