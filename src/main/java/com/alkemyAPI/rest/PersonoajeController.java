package com.alkemyAPI.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemyAPI.DTOs.PersonajeDTO;
import com.alkemyAPI.DTOs.PersonajeMostrarDTO;
import com.alkemyAPI.entities.Personaje;
import com.alkemyAPI.services.PersonajeService;

@RestController
@RequestMapping("/characters")
public class PersonoajeController {

	@Autowired
	private PersonajeService personajeService;

	@GetMapping
	public ResponseEntity<List<PersonajeMostrarDTO>> list() {
		List<PersonajeMostrarDTO> personajes = personajeService.listCharacters();
		return ResponseEntity.ok(personajes);
	}

	@GetMapping("/listAll")
	public ResponseEntity<List<Personaje>> listAll() {
		List<Personaje> personajes = personajeService.listAll();
		return ResponseEntity.ok(personajes);
	}

	@RequestMapping(value = "{personajeId}")

	public ResponseEntity<Personaje> findById(@PathVariable("personajeId") String id) {
		try {
			return ResponseEntity.ok(this.personajeService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping
	public ResponseEntity<Personaje> create(@RequestBody PersonajeDTO personajeDTO) {

		try {
			return new ResponseEntity<>(personajeService.save(personajeDTO), HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	public ResponseEntity<Personaje> edit(@RequestBody PersonajeDTO newPersonaje) throws Exception {
		return ResponseEntity.ok(personajeService.save(newPersonaje));
	}

	@DeleteMapping(value = "{personajeId}")
	public ResponseEntity<Void> delete(@PathVariable("personajeId") String id) {
		try {
			personajeService.delete(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping(value = "/name={nombre}")
	public ResponseEntity<List<Personaje>> findByNombre(@PathVariable("nombre") String nombre) {
		return ResponseEntity.ok(personajeService.findByNombre(nombre));
	}

	@GetMapping(value = "/age={edad}")
	public ResponseEntity<List<Personaje>> findByEdad(@PathVariable("edad") Integer edad) {
		return ResponseEntity.ok(personajeService.findByEdad(edad));
	}

	@GetMapping(value = "/movie={idMovie}")
	public ResponseEntity<List<Personaje>> findByMovie(@PathVariable("idMovie") String idMovie) {
		try {
			return ResponseEntity.ok(personajeService.findByIdMovie(idMovie));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
