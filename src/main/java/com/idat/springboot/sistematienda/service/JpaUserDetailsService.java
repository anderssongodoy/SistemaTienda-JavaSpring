package com.idat.springboot.sistematienda.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idat.springboot.sistematienda.repository.UsuarioRepository;
import com.idat.springboot.sistematienda.entity.Role;
import com.idat.springboot.sistematienda.entity.Usuario;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Autowired
	private UsuarioRepository usuarioRepositorio ;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Usuario usuario =  usuarioRepositorio.findByUsername(username);
		
		List<GrantedAuthority> autorithies = new ArrayList<GrantedAuthority>();
		
		for(Role roles: usuario.getRoles()) {
			autorithies.add(new SimpleGrantedAuthority(roles.getAuthority()));
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(),
				true,true,true, autorithies);
	}
}
