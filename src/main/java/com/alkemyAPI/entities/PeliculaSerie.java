package com.alkemyAPI.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
@Table(name = "peliculas_series")
public class PeliculaSerie {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String titulo;
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	private int calificacion;
	@JoinTable(
			name = "rel_peliculas_personajes")
	@OneToMany
	private List<Personaje> personajes;
	@Column(length = 16777215)
	private byte[] imagen;

}
