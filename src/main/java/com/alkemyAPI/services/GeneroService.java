package com.alkemyAPI.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemyAPI.DAO.GeneroRepository;
import com.alkemyAPI.entities.Genero;

@Service
public class GeneroService {
	@Autowired
	private GeneroRepository generoRepository;
	
	public List<Genero> listAll(){
		return generoRepository.findAll();
	}
	
	@Transactional
	public Genero save(Genero genero) {
		return generoRepository.save(genero);
	}
}
