package com.idat.springboot.sistematienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.springboot.sistematienda.entity.Producto;
import com.idat.springboot.sistematienda.repository.ProductosRepository;

@Service
public class ProductosService {

	@Autowired
	private ProductosRepository productoRepositorio;
	
	public List<Producto> listAll(String search){
		if(search != null) {
			return productoRepositorio.findAll(search);
		}
		return productoRepositorio.findAll();
	}
	
	public void save(Producto producto) {
		productoRepositorio.save(producto);
	}
	
	public Producto get(String codigo) {
		return productoRepositorio.findById(codigo).get();
	}
	
	public void delete(String codigo) {
		productoRepositorio.deleteById(codigo);
	}
}
