package com.idat.springboot.sistematienda.service;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.idat.springboot.sistematienda.entity.Usuario;
import com.idat.springboot.sistematienda.util.UsuarioRegistroDTO;

public interface UsuarioServicio extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
}
