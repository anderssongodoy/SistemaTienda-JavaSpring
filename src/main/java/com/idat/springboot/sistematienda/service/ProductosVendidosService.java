package com.idat.springboot.sistematienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.springboot.sistematienda.entity.ProductoVendido;
import com.idat.springboot.sistematienda.repository.ProductosVendidosRepository;

@Service
public class ProductosVendidosService {

	@Autowired
	private ProductosVendidosRepository productosvendidosRepositorio;
	
	public List<ProductoVendido> listAll(String search){
		if(search != null) {
			return productosvendidosRepositorio.findAll(search);
		}
		return productosvendidosRepositorio.findAll();
	}
	
	public void save(ProductoVendido productovendido) {
		productosvendidosRepositorio.save(productovendido);
	}
	
	public ProductoVendido get(String codigo) {
		return productosvendidosRepositorio.findById(codigo).get();
	}
	
	public void delete(String codigo) {
		productosvendidosRepositorio.deleteById(codigo);
	}
}
