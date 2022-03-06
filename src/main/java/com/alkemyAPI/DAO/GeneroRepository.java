package com.alkemyAPI.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemyAPI.entities.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, String>{

}
