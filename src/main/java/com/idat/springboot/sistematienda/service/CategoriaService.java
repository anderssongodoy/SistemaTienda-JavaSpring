package com.idat.springboot.sistematienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.springboot.sistematienda.entity.Categoria;
import com.idat.springboot.sistematienda.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepositorio;
	
	public List<Categoria> listAll(String search){
		if(search != null) {
			return categoriaRepositorio.findAll(search);
		}
		return categoriaRepositorio.findAll();
	}
	
	public void save(Categoria categoria) {
		categoriaRepositorio.save(categoria);
	}
	
	public Categoria get(String codigo) {
		return categoriaRepositorio.findById(codigo).get();
	}
	
	public void delete(String codigo) {
		categoriaRepositorio.deleteById(codigo);
	}
}
