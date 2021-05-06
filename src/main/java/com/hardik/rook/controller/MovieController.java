package com.hardik.rook.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.rook.entity.Movie;
import com.hardik.rook.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/movie")
public class MovieController {

	private final MovieService movieService;

	@PostMapping
	@Operation(summary = "Creates A Movie Document Inside Firebase And Returns It's Id")
	public ResponseEntity<?> movieCreationHandler(@RequestBody(required = true) final Movie movieId)
			throws JSONException {
		return movieService.createMovie(movieId);
	}

	@GetMapping("/{movieId}")
	@Operation(summary = "Returns A Movie Document Corresponding To Providied Id")
	public ResponseEntity<Movie> movieRetreivalHandler(
			@PathVariable(name = "movieId", required = true) final String movieId)
			throws InterruptedException, ExecutionException {
		return ResponseEntity.ok(movieService.retreive(movieId));
	}

	@PutMapping("/{movieId}")
	@Operation(summary = "Returns Updated Movie Document Corresponding To Provided Id")
	public ResponseEntity<?> movieUpdationHandler(@PathVariable(name = "movieId", required = true) final String movieId,
			@RequestBody(required = true) final Movie movie)
			throws InterruptedException, ExecutionException, JSONException {
		return movieService.update(movieId, movie);
	}

}
