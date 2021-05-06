package com.hardik.rook.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.rook.entity.DirectorMovieMapping;
import com.hardik.rook.entity.Movie;
import com.hardik.rook.service.DirectorMovieMappingService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/movie-director-mapping")
public class DirectorMovieMappingController {

	private final DirectorMovieMappingService directorMovieMappingService;

	@PostMapping
	@Operation(summary = "Maps The Director And Movie In The Firebase Collection")
	public ResponseEntity<?> directorMovieMappingCreationHandler(
			@RequestBody(required = true) final DirectorMovieMapping directorMovieMapping) throws JSONException {
		return directorMovieMappingService.createMapping(directorMovieMapping);
	}

	@GetMapping("/{directorId}")
	@Operation(summary = "Returns List of Movies Directed By The Provided Director")
	public ResponseEntity<List<Movie>> getMoviesByDirectorId(
			@PathVariable(required = true, name = "directorId") final String directorId)
			throws InterruptedException, ExecutionException {
		return ResponseEntity.ok(directorMovieMappingService.retreiveMoviesByDirectorId(directorId));
	}

	@DeleteMapping("/{mappingId}")
	@Operation(summary = "Deletes Specified Mapping Of Movie And Director")
	public ResponseEntity<?> directorMovieMappingDeletionHandler(
			@PathVariable(required = true, name = "mappingId") final String mappingId) throws JSONException {
		return directorMovieMappingService.delete(mappingId);
	}

}
