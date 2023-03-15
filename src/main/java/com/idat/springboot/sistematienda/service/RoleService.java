package com.idat.springboot.sistematienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.springboot.sistematienda.entity.Role;
import com.idat.springboot.sistematienda.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepositorio;
	
	public List<Role> listAll(String search){
		if(search != null) {
			return roleRepositorio.findAll(search);
		}
		return roleRepositorio.findAll();
	}
	
	public void save(Role role) {
		roleRepositorio.save(role);
	}
	
	public Role get(String codigo) {
		return roleRepositorio.findById(codigo).get();
	}
	
	public void delete(String codigo) {
		roleRepositorio.deleteById(codigo);
	}
}
