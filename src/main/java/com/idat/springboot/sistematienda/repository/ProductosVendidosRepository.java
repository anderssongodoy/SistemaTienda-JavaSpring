package com.idat.springboot.sistematienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idat.springboot.sistematienda.entity.ProductoVendido;

public interface ProductosVendidosRepository extends JpaRepository<ProductoVendido, String> {

	@Query("SELECT p FROM ProductoVendido p WHERE"
			+ " CONCAT(p.codigo, p.cantidad, p.precio, p.descripcion, p.venta)"
			+ " LIKE %?1%")
	public List<ProductoVendido> findAll(String search);
}
