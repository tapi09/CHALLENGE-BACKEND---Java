package com.alkemyAPI.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemyAPI.DAO.PeliculaSerieRepository;
import com.alkemyAPI.DTOs.PeliculaSerieDTO;
import com.alkemyAPI.DTOs.PeliculaSerieDTOcomplet;
import com.alkemyAPI.entities.PeliculaSerie;

@Service
public class PeliculaSerieService {
	@Autowired
	private PeliculaSerieRepository peliculaSerieRepository;

	public List<PeliculaSerie> listAll() {
		return peliculaSerieRepository.findAll();
	}

	public List<PeliculaSerieDTO> listDTO() {
		List<PeliculaSerie> peliculas = listAll();
		List<PeliculaSerieDTO> listaReduc = new ArrayList<>();
		for (PeliculaSerie peliculaSerie : peliculas) {
			PeliculaSerieDTO dto = new PeliculaSerieDTO();
			dto.setImagen(peliculaSerie.getImagen());
			dto.setTitulo(peliculaSerie.getTitulo());
			dto.setFechaCreacion(peliculaSerie.getFechaCreacion());
			listaReduc.add(dto);
		}
		return listaReduc;
	}

	@Transactional
	public PeliculaSerie save(PeliculaSerieDTOcomplet peliculaSerieDTOcomplet) throws Exception {

		return peliculaSerieRepository.save(convert(peliculaSerieDTOcomplet));
	}

	private void validate(PeliculaSerie peliculaSerie) throws Exception {
		if (peliculaSerie != null) {
			if (peliculaSerie.getCalificacion() < 1 || peliculaSerie.getCalificacion() > 5) {
				System.out.println("La calificacion solo puede ser del 1 al 5");
				throw new Exception("La calificacion solo puede ser del 1 al 5");
			}
		}

	}

	@Transactional
	public void delete(String id) throws Exception {
		peliculaSerieRepository.delete(findById(id));
	}

	public PeliculaSerie findById(String Id) throws Exception {
		Optional<PeliculaSerie> optional = peliculaSerieRepository.findById(Id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new Exception("Id no encontrado en la base de datos");
		}
	}

	public List<PeliculaSerie> findByNombre(String nombre) {
		return peliculaSerieRepository.findByNombre(nombre);
	}

	public PeliculaSerie convert(PeliculaSerieDTOcomplet peliculaSerieDTOcomplet) throws Exception {
		PeliculaSerie peliculaSerie = new PeliculaSerie();
		if (peliculaSerieDTOcomplet.getId() != null) {
			peliculaSerie = findById(peliculaSerieDTOcomplet.getId());
		}
		if (peliculaSerieDTOcomplet.getFechaCreacion() != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha = simpleDateFormat.parse(peliculaSerieDTOcomplet.getFechaCreacion());

			peliculaSerie.setFechaCreacion(fecha);
		}
		peliculaSerie.setTitulo(peliculaSerieDTOcomplet.getTitulo());
		peliculaSerie.setCalificacion(peliculaSerieDTOcomplet.getCalificacion());
		peliculaSerie.setPersonajes(peliculaSerieDTOcomplet.getPersonajes());
		peliculaSerie.setImagen(peliculaSerieDTOcomplet.getImagen());

		validate(peliculaSerie);
		return peliculaSerie;

	}

	public List<PeliculaSerie> findByGenero(String genero) throws Exception {
		return peliculaSerieRepository.findByGenero(genero);
	}

	public List<PeliculaSerie> findByFechaAsc() throws Exception {
		return peliculaSerieRepository.findByFechaAsc();
	}

	public List<PeliculaSerie> findByFechaDesc() throws Exception {
		return peliculaSerieRepository.findByFechaDesc();
	}

}
