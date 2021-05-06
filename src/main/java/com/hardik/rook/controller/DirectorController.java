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

import com.hardik.rook.entity.Director;
import com.hardik.rook.service.DirectorService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/director")
public class DirectorController {

	private final DirectorService directorService;

	@PostMapping
	@Operation(summary = "Creates A Director Document Inside Firebase And Returns It's Id")
	public ResponseEntity<?> directorCreationHandler(@RequestBody(required = true) final Director director)
			throws JSONException {
		return directorService.createDirector(director);
	}

	@GetMapping("/{directorId}")
	@Operation(summary = "Returns A Director Document Corresponding To Providied Id")
	public ResponseEntity<Director> directorRetreivalHandler(
			@PathVariable(name = "directorId", required = true) final String directorId)
			throws InterruptedException, ExecutionException {
		return ResponseEntity.ok(directorService.retreive(directorId));
	}

	@PutMapping("/{directorId}")
	@Operation(summary = "Returns Updated Director Document Corresponding To Provided Id")
	public ResponseEntity<?> directorUpdationHandler(
			@PathVariable(name = "directorId", required = true) final String directorId,
			@RequestBody(required = true) final Director director)
			throws InterruptedException, ExecutionException, JSONException {
		return directorService.update(directorId, director);
	}

}
