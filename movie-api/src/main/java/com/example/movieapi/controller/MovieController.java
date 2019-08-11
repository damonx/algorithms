package com.example.movieapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.movieapi.model.Movie;
import com.example.movieapi.model.repository.MovieRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private final MovieRepository movieRepository;

	public MovieController(final MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@GetMapping
	public Flux<Movie> getMovies() {
		return this.movieRepository.findAll();
	}

	@GetMapping("{id}")
	public Mono<ResponseEntity<Movie>> getMovie(@PathVariable final String id) {
		return this.movieRepository.findById(id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Movie> saveMovie(@RequestBody final Movie movie) {
		return this.movieRepository.save(movie);
	}

	@PutMapping("{id}")
	public Mono<ResponseEntity<Movie>> updateMovie(@PathVariable(value = "id") final String id, @RequestBody final Movie movie) {
		return this.movieRepository.findById(id)
				.flatMap(existingMovie -> {
					existingMovie.setName(movie.getName());
					existingMovie.setGenre(movie.getGenre());
					existingMovie.setReleaseDate(movie.getReleaseDate());
					return movieRepository.save(existingMovie);
				})
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("{id}")
	public Mono<ResponseEntity<Void>> deleteMovie(@PathVariable(value = "id") final String id) {
		return this.movieRepository.findById(id)
				.flatMap(existingMovie -> movieRepository.delete(existingMovie)
						.then(Mono.just(ResponseEntity.ok().<Void>build())))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping
	public Mono<Void> deleteAllMovies() {
		return this.movieRepository.deleteAll();
	}
}
