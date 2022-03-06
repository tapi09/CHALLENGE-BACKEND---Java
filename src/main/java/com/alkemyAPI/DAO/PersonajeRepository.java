package com.alkemyAPI.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.alkemyAPI.entities.Personaje;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, String> {
	@Query("SELECT p FROM Personaje p WHERE p.nombre = :nombre")
	List<Personaje> findByNombre(String nombre);

	@Query("SELECT p FROM Personaje p WHERE p.edad = :edad")
	List<Personaje> findByEdad(Integer edad);

	@Query(value ="SELECT * FROM personajes p WHERE p.id IN (SELECT p2.personajes_id FROM rel_peliculas_personajes p2 WHERE p2.pelicula_serie_id = :idMovie)", nativeQuery = true)
	List<Personaje> findByIdMovie(@PathVariable String idMovie);

}
