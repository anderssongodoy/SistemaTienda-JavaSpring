package com.idat.springboot.sistematienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idat.springboot.sistematienda.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{

	@Query("SELECT c FROM Cliente c WHERE"
			+ " CONCAT(c.codigo, c.nombre, c.apellido, c.direccion, c.dni, c.correo, c.telefono)"
			+ " LIKE %?1%")
	public List<Cliente> findAll(String search);
}
