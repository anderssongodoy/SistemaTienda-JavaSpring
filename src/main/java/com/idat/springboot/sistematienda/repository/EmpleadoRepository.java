package com.idat.springboot.sistematienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idat.springboot.sistematienda.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, String>{

	@Query("SELECT e FROM Empleado e WHERE"
			+ " CONCAT(e.codigo, e.nombre, e.apellido, e.nacimiento, e.contrato, e.dni)"
			+ " LIKE %?1%")
	public List<Empleado> findAll(String search);
}
