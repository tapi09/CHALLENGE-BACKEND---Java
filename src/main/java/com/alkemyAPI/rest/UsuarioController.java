package com.alkemyAPI.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UsuarioController(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping("/users/")
	public void saveUsuario(@RequestBody Usuario user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usuarioRepository.save(user);
	}

	@GetMapping("/users/")
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	@GetMapping("/users/{username}")
	public Usuario getUsuario(@PathVariable String username) {
		return usuarioRepository.findByUsername(username);
	}
	@PostMapping("auth/register")
	public ResponseEntity<Usuario> save(@RequestBody Usuario user){
		try {
			
		return  ResponseEntity.ok(usuarioService.save(user));
		}catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
		 
	}
	@GetMapping("/list")
	public List<Usuario> usuarios(){
		return usuarioService.listarTodos();
	}
}
