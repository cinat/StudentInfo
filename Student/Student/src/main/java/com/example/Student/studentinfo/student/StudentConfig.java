package com.example.Student.studentinfo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {
        return args -> {
            Student Maria=
            new Student(1L,
                    "Maria",
                    "Maria@gmail.com",
                    LocalDate.of(2000, Month.OCTOBER, 6)

            );
            repository.saveAll(List.of(Maria));
        };
    }
}

