package com.idat.springboot.sistematienda.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

// CREACION DE LA ENTIDAD Y TABLA CARGO
@Entity
@Table(name="Cargo", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"codigo_empleado","Descripcion"}) // RELACION DEL FK
})
public class Cargo {

	@Id // PRIMARY KEY
	@Column(name="CodCargo") // RENOMBRE DE LA COLUMNA POR CODCARGO
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_cargo") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_cargo", 
		strategy = "com.idat.springboot.app.model.entity.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR
			
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "Cr"),  // EL PREFIJO SERA PR
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%03d") // LOS DIGITOS SERAN 3 ( MAXIMO = 999 )
	})
	@Size(max=5) // TAMAÑO MAXIMO 5
	private String codigo; // ATRIBUTO CODIGO
	
	@Column(name="Descripcion") // RENOMBRE DE LA COLUMNA DESCRIPCION
	@NotBlank(message="No olvides escribir la descripcion del cargo") // MENSAJE POR SI NO SE COMPLETA EL CAMPO
	@Size(min=5, max=100) // TAMAÑO MINIMO 5 Y MAXIMO 100
	private String descripcion; // ATRIBUTO DESCRIPCION

// GETTERS AND SETTERS POR CADA ATRIBUTO
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
