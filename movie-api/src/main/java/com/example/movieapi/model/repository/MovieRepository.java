package com.example.movieapi.model.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.movieapi.model.Movie;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

}
