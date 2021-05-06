package com.hardik.rook.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.cloud.firestore.Firestore;
import com.hardik.rook.entity.DirectorMovieMapping;
import com.hardik.rook.entity.Movie;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DirectorMovieMappingService {

	private final Firestore firestore;

	public ResponseEntity<?> createMapping(final DirectorMovieMapping directorMovieMapping) throws JSONException {
		final var response = new JSONObject();
		final var mappingId = RandomStringUtils.randomAlphanumeric(10).toUpperCase();

		firestore.collection("director_movie_mappings").document(mappingId).set(directorMovieMapping);

		response.put("id", mappingId);
		response.put("timestamp", LocalDateTime.now());
		return ResponseEntity.ok(response.toString());
	}

	public List<Movie> retreiveMoviesByDirectorId(final String directorId)
			throws InterruptedException, ExecutionException {

		final var mappingList = firestore.collection("director_movie_mappings").whereEqualTo("director_id", directorId)
				.get().get().getDocuments();

		return mappingList.stream().map(mapping -> {
			final var directorMovieMapping = mapping.toObject(DirectorMovieMapping.class);

			try {
				return firestore.collection("movies").document(directorMovieMapping.getMovie_id()).get().get()
						.toObject(Movie.class);
			} catch (InterruptedException | ExecutionException e) {
				return null;
			}
		}).collect(Collectors.toList());
	}

	public ResponseEntity<?> delete(String mappingId) throws JSONException {
		final var response = new JSONObject();

		firestore.collection("director_movie_mappings").document(mappingId).delete();

		response.put("message", "Mapping Id: " + mappingId + " Successfully Deleted");
		response.put("timestamp", LocalDateTime.now());
		return ResponseEntity.ok(response.toString());
	}

}
