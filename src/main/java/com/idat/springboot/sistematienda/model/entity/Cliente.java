package com.idat.springboot.sistematienda.model.entity;

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

// CREACION DE LA ENTIDAD Y TABLA CLIENTE
@Entity
@Table(name="Cliente")
public class Cliente{ 
	
	@Id // PRIMARY KEY
	@Column(name="CodCliente") // RENOMBRE DE LA COLUMNA POR CODCLIENTE
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_cliente") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_cliente", 
		strategy = "com.idat.springboot.app.model.entity.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR
			
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "C"),  // EL PREFIJO SERA C
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d") // LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )
	})
	@Size(max=5) // TAMAÑO MAXIMO 5
	private String codigo; // ATRIBUTO CODIGO
	
	@Column(name="Nombre") // RENOMBRE DE LA COLUMNA POR NOMBRE
	@NotBlank(message="No olvides escribir el Nombre") // MENSAJE POR SI NO SE COMPLETA EL CAMPO
	@Size(min=5, max=20) // TAMAÑO MINIMO 5 Y MAXIMO 20
	private String nombre; // ATRIBUTO NOMBRE
	
	@Column(name="Apellido") // RENOMBRE DE LA COLUMNA POR APELLIDO
	@NotBlank(message="No olvides escribir el Apellido") // MENSAJE POR SI NO SE COMPLETA EL CAMPO
	@Size(min=4, max=20) // TAMAÑO MINIMO 4 Y MAXIMO 20
	private String apellido; // ATRIBUTO APELLIDO
	
	@Column(name="Direccion") // RENOMBRE DE LA COLUMNA POR DIRECCION
	@Size(max=50) // TAMAÑO MAXIMO 50
	private String direccion; // ATRIBUTO DIRECCION
	
	@Column(name="DNI") // RENOMBRE DE LA COLUMNA POR DNI
	@NotBlank(message="No olvides escribir el DNI") // MENSAJE POR SI NO SE COMPLETA EL CAMPO
	@Size(min=8, max=8) // TAMAÑO MINIMO 8 Y MAXIMO 8
	private String dni; // ATRIBUTO DNI
	
	@Column(name="Correo") // RENOMBRE DE LA COLUMNA POR CORREO
	@NotBlank(message="No olvides escribir el Correo") // MENSAJE POR SI NO SE COMPLETA EL CAMPO
	@Size(max=50) // TAMAÑO MAXIMO 50
	private String correo; // ATRIBUTO CORREO
	
	@Column(name="Telefono") // RENOMBRE DE LA COLUMNA POR TELEFONO
	@Size(min=9, max=9) // TAMAÑO MINIMO 9 Y MAXIMO 9
	private String telefono; // ATRIBUTO TELEFONO

// GETTERS AND SETTERS POR CADA ATRIBUTO
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}
