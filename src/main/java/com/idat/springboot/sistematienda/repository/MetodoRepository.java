package com.idat.springboot.sistematienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idat.springboot.sistematienda.entity.MetodoPago;

public interface MetodoRepository extends JpaRepository<MetodoPago, String>{

	@Query("SELECT m FROM MetodoPago m WHERE"
			+ " CONCAT(m.codigo, m.descripcion)"
			+ " LIKE %?1%")
	public List<MetodoPago> findAll(String search);
}
