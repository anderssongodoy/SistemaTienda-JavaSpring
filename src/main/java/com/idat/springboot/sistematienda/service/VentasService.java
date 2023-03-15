package com.idat.springboot.sistematienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.springboot.sistematienda.entity.Venta;
import com.idat.springboot.sistematienda.repository.VentasRepository;

@Service
public class VentasService {

	@Autowired
	private VentasRepository ventasRepositorio;
	
	public List<Venta> listAll(String search){
		if(search != null) {
			return ventasRepositorio.findAll(search);
		}
		return ventasRepositorio.findAll();
	}
	
	public void save(Venta venta) {
		ventasRepositorio.save(venta);
	}
	
	public Venta get(String codigo) {
		return ventasRepositorio.findById(codigo).get();
	}
	
	public void delete(String codigo) {
		ventasRepositorio.deleteById(codigo);
	}
}
