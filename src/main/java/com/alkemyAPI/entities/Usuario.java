package com.alkemyAPI.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.alkemyAPI.enums.Role;

import lombok.Data;
@Entity
@Data
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String email;

	private String password;
	
	private Role rol;
	
}
