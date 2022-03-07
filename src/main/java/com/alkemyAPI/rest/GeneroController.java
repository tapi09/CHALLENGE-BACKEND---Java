package com.alkemyAPI.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemyAPI.entities.Genero;
import com.alkemyAPI.services.GeneroService;

@RestController
@RequestMapping("/genero")
public class GeneroController {
	@Autowired
	private GeneroService generoService;

	@GetMapping
	public ResponseEntity<List<Genero>> listAll() {
		return ResponseEntity.ok(generoService.listAll());
	}

	@PostMapping
	public ResponseEntity<Genero> save(@RequestBody Genero genero) {
		return ResponseEntity.ok(generoService.save(genero));
	}

	@DeleteMapping(value = "{generoId}")
	public ResponseEntity<Void> delete(@PathVariable("generoId") String id) {
		try {
			generoService.delete(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}
