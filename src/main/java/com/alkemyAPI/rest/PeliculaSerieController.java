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

import com.alkemyAPI.DTOs.PeliculaSerieDTO;
import com.alkemyAPI.DTOs.PeliculaSerieDTOcomplet;
import com.alkemyAPI.entities.PeliculaSerie;
import com.alkemyAPI.services.PeliculaSerieService;

@RestController
@RequestMapping("/movies")
public class PeliculaSerieController {
	@Autowired
	private PeliculaSerieService peliculaSerieService;

	@GetMapping
	public ResponseEntity<List<PeliculaSerieDTO>> listDto() {
		return ResponseEntity.ok(peliculaSerieService.listDTO());
	}

	@GetMapping("/listAll")
	public ResponseEntity<List<PeliculaSerie>> listAll() {
		return ResponseEntity.ok(peliculaSerieService.listAll());
	}

	@RequestMapping(value = "{peliculaId}")
	public ResponseEntity<PeliculaSerie> findById(@PathVariable("peliculaId") String id) {
		try {
			return ResponseEntity.ok(peliculaSerieService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping
	public ResponseEntity<PeliculaSerie> save(@RequestBody PeliculaSerieDTOcomplet peliculaSerieDTO) {
		try {
			return ResponseEntity.ok(peliculaSerieService.save(peliculaSerieDTO));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping(value = "{peliculaId}")
	public ResponseEntity<Void> delete(@PathVariable("peliculaId") String id) {
		try {
			peliculaSerieService.delete(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(value = "/name={nombre}")
	public ResponseEntity<List<PeliculaSerie>> findByNombre(@PathVariable("nombre") String nombre) {
		return ResponseEntity.ok(peliculaSerieService.findByNombre(nombre));
	}

	@GetMapping(value = "/genre={idGenero}")
	public ResponseEntity<List<PeliculaSerie>> findByGenero(@PathVariable("idGenero") String genero) {
		try {
			return ResponseEntity.ok(peliculaSerieService.findByGenero(genero));
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
	@GetMapping(value= "/order=ASC")
	public ResponseEntity<List<PeliculaSerie>> findByFechaAsc(){
		try {
			return ResponseEntity.ok(peliculaSerieService.findByFechaAsc());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	@GetMapping(value= "/order=DESC")
	public ResponseEntity<List<PeliculaSerie>> findByFechaDesc(){
		try {
			return ResponseEntity.ok(peliculaSerieService.findByFechaDesc());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

}
