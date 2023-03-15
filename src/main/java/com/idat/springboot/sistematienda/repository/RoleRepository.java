package com.idat.springboot.sistematienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idat.springboot.sistematienda.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

	@Query("SELECT r FROM Role r WHERE"
			+ " CONCAT(r.codigo, r.authority, r.empleado, r.user_id)"
			+ " LIKE %?1%")
	public List<Role> findAll(String search);
}
