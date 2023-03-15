package com.idat.springboot.sistematienda.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Empleado")
public class Empleado {

	@Id // PRIMARY KEY
	@Column(name="CodEmpleado") // RENOMBRE DE LA COLUMNA POR CODCATEGORIA
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_empleado") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_empleado", 
		strategy = "com.idat.springboot.sistematienda.entity.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR
			
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "Em"),  // EL PREFIJO SERA E
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%03d") // LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )
	})
	@Size(max=5) // TAMAÑO MAXIMO 5
	private String codigo; // ATRIBUTO CODIGO
	
	@Column(name="Nombre") // RENOMBRE DE LA COLUMNA POR NOMBRE
	@NotBlank(message="No olvides escribir el Nombre") // MENSAJE POR SI NO SE COMPLETA EL CAMPO
	@Size(min=3, max=50, message="El tamaño debe estar entre 3 y 50 caracteres") // TAMAÑO MINIMO 3 Y MAXIMO 50
	private String nombre; // ATRIBUTO NOMBRE
	
	@Column(name="Apellido") // RENOMBRE DE LA COLUMNA POR APELLIDO
	@NotBlank(message="No olvides escribir el Apellido") // MENSAJE POR SI NO SE COMPLETA EL CAMPO
	@Size(min=3, max=50, message="El tamaño debe estar entre 3 y 50 caracteres") // TAMAÑO MINIMO 3 Y MAXIMO 50
	private String apellido; // ATRIBUTO APELLIDO
	
	@Column(name="FechaNacimiento") // RENOMBRE DE LA COLUMNA POR FECHANACIMIENTO
	//@Temporal(TemporalType.DATE) // DETERMINAMOS QUE ALMACENA UNA FECHA
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // FORMATO DE FECHA
	@Past // VALIDAMOS QUE LA FECHA SEA PASADA
	private LocalDate nacimiento; // ATRIBUTO NACIMIENTO
	
	@Column(name="FechaContrato") // RENOMBRE DE LA COLUMNA POR FECHACONTRATO
	//@Temporal(TemporalType.DATE)  // DETERMINAMOS QUE ALMACENA UNA FECHA
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // FORMATO DE FECHA
	@FutureOrPresent // VALIDAMOS QUE LA FECHA SEA HOY O EN UN FUTURO
	private LocalDate contrato; // ATRIBUTO CONTRATO
	
	@Column(name="DNI") // RENOMBRE DE LA COLUMNA POR DNI
	@NotBlank(message="No olvides escribir el DNI") // MENSAJE POR SI NO SE COMPLETA EL CAMPO
	@Size(min=8, max=8, message="El tamaño debe ser de 8 caracteres") // TAMAÑO MINIMO 8 Y MAXIMO 8
	private String dni; // ATRIBUTO DNI
	

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

	public LocalDate getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(LocalDate nacimiento) {
		this.nacimiento = nacimiento;
	}

	public LocalDate getContrato() {
		return contrato;
	}

	public void setContrato(LocalDate contrato) {
		this.contrato = contrato;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
}
