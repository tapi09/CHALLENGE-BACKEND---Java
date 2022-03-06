package com.alkemyAPI.DAO;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.alkemyAPI.entities.PeliculaSerie;

@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerie, String> {
	
	@Query("SELECT p FROM PeliculaSerie p WHERE p.titulo = :nombre")
	List<PeliculaSerie> findByNombre(String nombre);

	@Query(value = "SELECT * FROM peliculas_series p WHERE p.id in (SELECT g.peliculas_id FROM genero_peliculas g WHERE g.genero_id = :idGenero)", nativeQuery = true) 
	List<PeliculaSerie> findByGenero(@PathVariable String idGenero);
	 
	@Query(value = "select * from peliculas_series order by  fecha_creacion asc", nativeQuery = true) 
	List<PeliculaSerie> findByFechaAsc();
	
	@Query(value = "select * from peliculas_series order by  fecha_creacion desc", nativeQuery = true) 
	List<PeliculaSerie> findByFechaDesc();
	 

}
