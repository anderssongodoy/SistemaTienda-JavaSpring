package com.idat.springboot.sistematienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.springboot.sistematienda.entity.Cliente;
import com.idat.springboot.sistematienda.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepositorio;
	
	public List<Cliente> listAll(String search){
		if(search != null) {
			return clienteRepositorio.findAll(search);
		}
		return clienteRepositorio.findAll();
	}
	
	public void save(Cliente categoria) {
		clienteRepositorio.save(categoria);
	}
	
	public Cliente get(String codigo) {
		return clienteRepositorio.findById(codigo).get();
	}
	
	public void delete(String codigo) {
		clienteRepositorio.deleteById(codigo);
	}
}
