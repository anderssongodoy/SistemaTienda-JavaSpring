package com.idat.springboot.sistematienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.springboot.sistematienda.entity.Proveedor;
import com.idat.springboot.sistematienda.repository.ProveedorRepository;

@Service
public class ProveedorService {
	
	@Autowired
	private ProveedorRepository proveedorRepositorio;
	
	public List<Proveedor> listAll(String search){
		if(search != null) {
			return proveedorRepositorio.findAll(search);
		}
		return proveedorRepositorio.findAll();
	}
	
	public void save(Proveedor proveedor) {
		proveedorRepositorio.save(proveedor);
	}
	
	public Proveedor get(String codigo) {
		return proveedorRepositorio.findById(codigo).get();
	}
	
	public void delete(String codigo) {
		proveedorRepositorio.deleteById(codigo);
	}

}
