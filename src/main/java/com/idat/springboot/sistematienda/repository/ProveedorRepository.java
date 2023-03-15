package com.idat.springboot.sistematienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idat.springboot.sistematienda.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, String>{

	@Query("SELECT p FROM Proveedor p WHERE"
			+ " CONCAT(p.codigo, p.empresa, p.contacto, p.descripcion, p.telefono)"
			+ " LIKE %?1%")
	public List<Proveedor> findAll(String search);
}
