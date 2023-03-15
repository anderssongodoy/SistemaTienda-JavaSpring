package com.idat.springboot.sistematienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.springboot.sistematienda.entity.Empleado;
import com.idat.springboot.sistematienda.repository.EmpleadoRepository;

@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepositorio;
	
	public List<Empleado> listAll(String search){
		if(search != null) {
			return empleadoRepositorio.findAll(search);
		}
		return empleadoRepositorio.findAll();
	}
	
	public void save(Empleado empleado) {
		empleadoRepositorio.save(empleado);
	}
	
	public Empleado get(String codigo) {
		return empleadoRepositorio.findById(codigo).get();
	}
	
	public void delete(String codigo) {
		empleadoRepositorio.deleteById(codigo);
	}
}
