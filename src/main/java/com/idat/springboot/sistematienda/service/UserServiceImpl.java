package com.idat.springboot.sistematienda.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.idat.springboot.sistematienda.entity.Role;
import com.idat.springboot.sistematienda.entity.Usuario;
import com.idat.springboot.sistematienda.repository.UsuarioRepository;
import com.idat.springboot.sistematienda.util.UsuarioRegistroDTO;

@Service
public class UserServiceImpl implements UsuarioServicio{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private UsuarioRepository usuarioRepositorio;
	
	public UserServiceImpl(UsuarioRepository usuarioRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
	}

	@Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO) {
		Usuario usuario = new Usuario(registroDTO.getUsername(), passwordEncoder.encode(registroDTO.getPassword()),
				registroDTO.getEnabled(), registroDTO.getEmpleado(), registroDTO.getCorreo(), Arrays.asList(new Role(null)));
		return usuarioRepositorio.save(usuario);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByUsername(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getUsername(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
		
	}
	
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
	}



	
}
