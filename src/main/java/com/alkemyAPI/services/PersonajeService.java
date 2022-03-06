package com.alkemyAPI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemyAPI.DAO.PersonajeRepository;
import com.alkemyAPI.DTOs.PersonajeDTO;
import com.alkemyAPI.DTOs.PersonajeMostrarDTO;
import com.alkemyAPI.entities.Personaje;

@Service
public class PersonajeService {
	@Autowired
	private PersonajeRepository personajeRepository;
	

	public List<Personaje> listAll() {
		return personajeRepository.findAll();

	}

	public Personaje findById(String id) throws Exception {
		Optional<Personaje> optional = personajeRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new Exception("id no encontrado");
		}

	}

	@Transactional
	public Personaje save(PersonajeDTO personajeDTO) throws Exception {
		return personajeRepository.save(convert(personajeDTO));
	}
	public Personaje convert(PersonajeDTO personajeDTO) throws Exception {
		Personaje personaje = new Personaje();
		if(personajeDTO.getId() != null) {
			personaje = findById(personajeDTO.getId());
		}
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setEdad(personajeDTO.getEdad());
		personaje.setPeso(personajeDTO.getPeso());
		personaje.setHistoria(personajeDTO.getHistoria());
		personaje.setPeliculaSerie(personajeDTO.getPeliculas());
		personaje.setImagen(personajeDTO.getImagen());
		
		return personaje;
	}
	

	public List<PersonajeMostrarDTO> listCharacters() {
		List<Personaje> personajes = listAll();
		List<PersonajeMostrarDTO> list = new ArrayList<>();
		for (Personaje personaje : personajes) {
			PersonajeMostrarDTO personajeDTO = new PersonajeMostrarDTO();
			personajeDTO.setNombre(personaje.getNombre());
			personajeDTO.setImagen(personaje.getImagen());
			list.add(personajeDTO);
		}
		return list;
	}

	@Transactional
	public void delete(String id) throws Exception {

		personajeRepository.delete(findById(id));
	}

	public List<Personaje> findByNombre(String nombre) {
		return personajeRepository.findByNombre(nombre);

	}

	public List<Personaje> findByEdad(Integer edad) {
		return personajeRepository.findByEdad(edad);

	}

	public List<Personaje> findByIdMovie(String idMovie) throws Exception {

		return personajeRepository.findByIdMovie(idMovie);

	}

}
