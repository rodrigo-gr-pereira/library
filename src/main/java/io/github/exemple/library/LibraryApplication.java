package io.github.exemple.library;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LibraryApplication {

	public static void main(String[] args) {
		 SpringApplication.run(LibraryApplication.class, args);
			}
}
