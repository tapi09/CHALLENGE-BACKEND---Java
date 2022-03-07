package com.alkemyAPI.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
@Table(name = "personajes")
public class Personaje {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(length = 50)
	private String nombre;

	private Integer edad;
	private Double peso;

	@Column(length = 1500)
	private String historia;

	@OneToMany(mappedBy = "personajes")
	private List<PeliculaSerie> peliculaSerie;

	@Column(length = 16777215)
	private byte[] imagen;
}
