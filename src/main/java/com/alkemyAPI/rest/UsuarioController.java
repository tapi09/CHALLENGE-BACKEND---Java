package com.alkemyAPI.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alkemyAPI.DAO.UsuarioRepository;
import com.alkemyAPI.entities.Usuario;
import com.alkemyAPI.services.UsuarioDetailsServiceImpl;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioDetailsServiceImpl usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/users/")
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	@GetMapping("/users/{email}")
	public Usuario getUsuario(@PathVariable String email) {
		return usuarioRepository.findByEmail(email);
	}

	@PostMapping("auth/register")
	public ResponseEntity<Usuario> save(@RequestBody Usuario user) {
		try {

			return ResponseEntity.ok(usuarioService.save(user));
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}

	}

}
