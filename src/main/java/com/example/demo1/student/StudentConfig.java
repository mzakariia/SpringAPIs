package com.example.demo1.student;

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
            Student mariam = new Student(
                    "mariam",
                    "gmail",
                    LocalDate.of(1, Month.FEBRUARY,5)
            );

            Student alex = new Student(
                    "alex",
                    "alex@gmail.com",
                    LocalDate.of(1, Month.FEBRUARY,5)
            );

            repository.saveAll(List.of(mariam,alex));
        };
    }
}
