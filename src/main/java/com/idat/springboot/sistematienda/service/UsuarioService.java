package com.idat.springboot.sistematienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.springboot.sistematienda.entity.Usuario;
import com.idat.springboot.sistematienda.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepositorio;
	
	public List<Usuario> listAll(String search){
		if(search != null) {
			return usuarioRepositorio.findAll(search);
		}
		return usuarioRepositorio.findAll();
	}
	
	public void save(Usuario usuario) {
		usuarioRepositorio.save(usuario);
	}
	
	public Usuario get(String codigo) {
		return usuarioRepositorio.findById(codigo).get();
	}
	
	public void delete(String codigo) {
		usuarioRepositorio.deleteById(codigo);
	}
}
