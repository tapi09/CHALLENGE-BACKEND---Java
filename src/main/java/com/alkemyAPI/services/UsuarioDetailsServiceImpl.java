package com.alkemyAPI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alkemyAPI.DAO.UsuarioRepository;
import com.alkemyAPI.entities.Usuario;
import com.alkemyAPI.enums.Role;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public Usuario save(Usuario usuario) throws Exception {
		validar(usuario.getUsername(), usuario.getPassword());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setUsername(usuario.getUsername());
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		usuario.setRol(Role.USUARIO);
		

		return usuarioRepository.save(usuario);
		 

	}

	private void validar(String username, String password) throws Exception {
		if (username.isEmpty() || username == null) {
			throw new Exception("El nombre de usuario no puede estar vacío");
		}
		if (buscarXUserName(username) != null) {
			throw new Exception("El nombre de usuario ingresado ya existe, por favor ingrese otro");
		}
		if (password.isEmpty() || password == null) {
			throw new Exception("La contraseña no puede estar vacía");
		}

	}

	public Usuario buscarXUserName(String username) {
		return usuarioRepository.findByUsername(username);
	}

	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Usuario usuario = buscarXUserName(username);
			List<GrantedAuthority> authorities = new ArrayList<>();
			GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_" + usuario.getRol());
			authorities.add(auth);
			return new User(username, usuario.getPassword(), authorities);
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException("Usuario o contraseña no encontrado");
		}
	}

	public String obtenernombre(Authentication usuario) throws Exception {
		try {
			return usuario.getName();
		} catch (Exception e) {
			throw new Exception("error al obtener nombre de usuario");
		}

	}

	public Usuario buscaruserxid(Long id) throws Exception {
		Optional<Usuario> respuesta = usuarioRepository.findById(id);
		if (respuesta.isPresent()) {
			return respuesta.get();
		} else {
			throw new Exception("Id no encontrado");
		}
	}
}
