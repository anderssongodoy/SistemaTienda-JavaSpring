package com.idat.springboot.sistematienda.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="authorities", uniqueConstraints= 
@UniqueConstraint(columnNames= "authority"))
public class Role implements Serializable{

	@Id // PRIMARY KEY
	@Column(name="CodRole") // RENOMBRE DE LA COLUMNA POR CODROLE
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_role") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_role", 
		strategy = "com.idat.springboot.sistematienda.entity.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR
			
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "Rl"),  // EL PREFIJO SERA RL
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%03d") // LOS DIGITOS SERAN 3 ( MAXIMO = 999 )
	})
	@Size(max=5) // TAMAÑO MAXIMO 5
	private String codigo;
	
	private String authority;	

	private String empleado;
	
	private String user_id;
	

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	

	public Role(String authority) {
		super();
		this.authority = authority;
	}

	public Role() {
		super();
	}

	public Role(@Size(max = 5) String codigo, String authority, String empleado, String user_id) {
		super();
		this.codigo = codigo;
		this.authority = authority;
		this.empleado = empleado;
		this.user_id = user_id;
	}



	private static final long serialVersionUID=1L;
}
