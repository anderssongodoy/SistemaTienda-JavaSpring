package com.idat.springboot.sistematienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idat.springboot.sistematienda.entity.Producto;

public interface ProductosRepository extends JpaRepository<Producto, String> {

    Producto findFirstByCodigo(String codigo);
    
    @Query("SELECT p FROM Producto p WHERE"
			+ " CONCAT(p.codigo, p.proveedor, p.categoria, p.descripcion, p.costo, p.venta, p.minimo, p.actual)"
			+ " LIKE %?1%")
	public List<Producto> findAll(String search);
}
