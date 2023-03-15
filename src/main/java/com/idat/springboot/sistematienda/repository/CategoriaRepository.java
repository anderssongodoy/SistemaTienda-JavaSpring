package com.idat.springboot.sistematienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idat.springboot.sistematienda.entity.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, String>{
	
	@Query("SELECT c FROM Categoria c WHERE"
			+ " CONCAT(c.codigo, c.descripcion, c.nombre)"
			+ " LIKE %?1%")
	public List<Categoria> findAll(String search);

}
