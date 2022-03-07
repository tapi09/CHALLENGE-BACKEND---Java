package com.alkemyAPI.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemyAPI.DAO.GeneroRepository;
import com.alkemyAPI.entities.Genero;

@Service
public class GeneroService {
	@Autowired
	private GeneroRepository generoRepository;

	public List<Genero> listAll() {
		return generoRepository.findAll();
	}

	@Transactional
	public Genero save(Genero genero) {
		return generoRepository.save(genero);
	}
	@Transactional
	public Genero delete(String id) throws Exception {
		generoRepository.delete(findById(id));
		return null;
	}

	private Genero findById(String id) throws Exception {
		Optional<Genero> optional = generoRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new Exception("Id no encontrado");
		}
	}
	
}
