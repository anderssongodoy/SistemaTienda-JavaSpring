package com.idat.springboot.sistematienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.idat.springboot.sistematienda.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	public Usuario findByUsername(String username);
	
	@Query("SELECT u FROM Usuario u WHERE"
			+ " CONCAT(u.codigo, u.username, u.password)"
			+ " LIKE %?1%")
	public List<Usuario> findAll(String search);
}
