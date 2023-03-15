package com.idat.springboot.sistematienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.springboot.sistematienda.entity.MetodoPago;
import com.idat.springboot.sistematienda.repository.MetodoRepository;

@Service
public class MetodoService {

	@Autowired
	private MetodoRepository metodoRepositorio;
	
	public List<MetodoPago> listAll(String search){
		if(search != null) {
			return metodoRepositorio.findAll(search);
		}
		return metodoRepositorio.findAll();
	}
	
	public void save(MetodoPago metodopago) {
		metodoRepositorio.save(metodopago);
	}
	
	public MetodoPago get(String codigo) {
		return metodoRepositorio.findById(codigo).get();
	}
	
	public void delete(String codigo) {
		metodoRepositorio.deleteById(codigo);
	}
}
