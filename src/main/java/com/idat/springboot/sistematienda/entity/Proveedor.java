package com.idat.springboot.sistematienda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

// CREACION DE LA ENTIDAD Y TABLA PROVEEDOR
@Entity
@Table(name="Proveedor")
public class Proveedor {
	
	@Id // PRIMARY KEY
	@Column(name="CodProveedor") // RENOMBRE DE LA COLUMNA POR CODPROVEEDOR
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_proveedor") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_proveedor", 
		strategy = "com.idat.springboot.sistematienda.entity.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR
			
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "Pr"),  // EL PREFIJO SERA PR
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%03d") // LOS DIGITOS SERAN 3 ( MAXIMO = 999 )
	})
	@Size(max=5) // TAMAÑO MAXIMO 5
	private String codigo; // ATRIBUTO CODIGO
	
	@Column(name="Empresa") // RENOMBRE DE LA COLUMNA EMPRESA
	@NotBlank(message="No olvides escribir el nombre de la empresa") // MENSAJE POR SI NO SE COMPLETA EL CAMPO
	@Size(min=5, max=30, message="El tamaño debe estar entre 5 y 30 caracteres") // TAMAÑO MINIMO 5 Y MAXIMO 30
	private String empresa; // ATRIBUTO EMPRESA
	
	@Column(name="Contacto") // RENOMBRE DE LA COLUMNA CONTACTO
	@Size(min=5, max=50, message="El tamaño debe estar entre 5 y 50 caracteres") // TAMAÑO MINIMO 5 Y MAXIMO 50
	private String contacto; // ATRIBUTO CONTACTO
	
	@NotBlank(message="No olvides escribir la descripcion de la empresa") // MENSAJE POR SI NO SE COMPLETA EL CAMPO
	@Size(min=5, max=100, message="El tamaño debe estar entre 5 y 100 caracteres") // TAMAÑO MINIMO 5 Y MAXIMO 50
	private String descripcion; // ATRIBUTO DESCRIPCION
	
	@Column(name="Telefono") // RENOMBRE DE LA COLUMNA TELEFONO
	@Size(max=13, message="El tamaño debe estar entre 0 y 13 caracteres") // TAMAÑO MINIMO 9 Y MAXIMO 12
	private String telefono;  // ATRIBUTO TELEFONO

// GETTERS AND SETTERS POR CADA ATRIBUTO
	
	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
