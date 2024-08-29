package com.monetize360.jpa;

import com.monetize360.jpa.models.Author;
import com.monetize360.jpa.repositories.AuthorRepository;
import com.monetize360.jpa.repositories.VideoRepository;
import com.monetize360.jpa.specification.AuthorSpecification;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import com.github.javafaker.Faker;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthorRepository repository,
			VideoRepository videoRepository
	) {
		return args -> {
			for (int i = 0; i < 50; i++) {
				Faker faker = new Faker();
				var author = Author.builder()
						.firstName(faker.name().firstName())
						.lastName(faker.name().lastName())
						.age(faker.number().numberBetween(20, 80))
						.email(faker.name().username() + "@aliboucoding.com")
						.build();
			    repository.save(author);
			}

			// update author with ID = 1
			var author = Author.builder()
					.id(1)
					.firstName("Ali")
					.lastName("Bouali")
					.age(34)
					.email("contact@aliboucoding.com")
					.build();
			 repository.save(author);

			// update Author a set a.age = 22 where a.id = 1
			// repository.updateAuthor(100, 1);

			// update all authors
			// repository.updateAllAuthorsAges(99);
			// find by named query
			// repository.findByNamedQuery(70).forEach(System.out::println);

			// update with named query
			// repository.updateByNamedQuery(12);

//			Specification<Author> spec = Specification
//					.where(AuthorSpecification.hasAge(22))
//					.or(AuthorSpecification.firstnameLike("i"))
//					;
//			repository.findAll((Sort) spec).forEach(System.out::println);
//			System.out.println(repository.findAllByFirstName("Ali"));
		};

	}

}
