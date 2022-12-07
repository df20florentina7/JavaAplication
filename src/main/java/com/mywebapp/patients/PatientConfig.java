package com.mywebapp.patients;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PatientConfig {
    @Bean
    CommandLineRunner commandLineRunner(PatientRepository repository){
        return args -> {
            Patient Karla= new Patient(4,"Karla", LocalDate.of(2004, Month.APRIL,13),"cold");
            Patient Ethan= new Patient(5,"Ethan", LocalDate.of(2000, Month.APRIL,20),"fever");
            repository.saveAll(List.of(Karla, Ethan));
        };
    }
}

