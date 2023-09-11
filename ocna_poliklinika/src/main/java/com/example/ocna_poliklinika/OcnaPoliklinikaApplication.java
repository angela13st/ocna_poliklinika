package com.example.ocna_poliklinika;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.example.ocna_poliklinika")
public class OcnaPoliklinikaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OcnaPoliklinikaApplication.class, args);
    }

}
