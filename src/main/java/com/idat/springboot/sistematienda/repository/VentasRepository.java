package com.idat.springboot.sistematienda.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idat.springboot.sistematienda.entity.Venta;

public interface VentasRepository extends JpaRepository<Venta, String> {

	@Query("SELECT v FROM Venta v WHERE"
			+ " CONCAT(v.codigo, v.producto, v.empleado, v.cliente, v.pago, v.fecha)"
			+ " LIKE %?1%")
	public List<Venta> findAll(String search);
}

