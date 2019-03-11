package com.example.movieapi;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

import com.example.movieapi.model.Movie;
import com.example.movieapi.model.repository.MovieRepository;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class MovieApiApplication {

	public static void main(final String[] args) {
		SpringApplication.run(MovieApiApplication.class, args);
	}

	@Bean
	CommandLineRunner init(@SuppressWarnings("unused") final ReactiveMongoOperations operations, final MovieRepository movieRepository) {
		return args -> {
			final Flux<Movie> productFlux = Flux.just(
					new Movie(null, "Avenger: Infinity Wars", "Action", LocalDateTime.now()),
					new Movie(null, "Gladiator", "Drama/Action", LocalDateTime.now()),
					new Movie(null, "Black Panther", "Action", LocalDateTime.now()))
					.flatMap(movieRepository::save);

			productFlux
					.thenMany(movieRepository.findAll())
					.subscribe(System.out::println);
		};
	}

}
