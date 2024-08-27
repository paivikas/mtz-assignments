package com.monetize360.jpa;

import com.monetize360.jpa.models.Author;
import com.monetize360.jpa.repositories.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}
	//@Bean
	public CommandLineRunner commandLineRunner(AuthorRepository repository){
		return args -> {
		 var author= Author.builder().firstName("Vikas").lastName("Pai").age(21).email("paivikas1402@gmail.com").build();
		 repository.save(author);

		};
	}

}
