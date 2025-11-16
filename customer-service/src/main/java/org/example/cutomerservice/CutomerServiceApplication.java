package org.example.cutomerservice;

import org.example.cutomerservice.entities.Customer;
import org.example.cutomerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class CutomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CutomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder().name("Souhail").email("souhail@gmail.com").build());
            customerRepository.save(Customer.builder().name("Salma").email("Salma@gmail.com").build());
            customerRepository.save(Customer.builder().name("Amine").email("Amine@gmail.com").build());

            customerRepository.findAll().forEach(c -> {
                System.out.println("*************");
                System.out.println(c.getId());
                System.out.println(c.getName());
                System.out.println(c.getEmail());
                System.out.println("*************");


            });
        };
    }
}
