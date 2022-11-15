package com.idat.springboot.sistematienda.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

public class Empleado {

	@Id // PRIMARY KEY
	@Column(name="CodEmpleado") // RENOMBRE DE LA COLUMNA POR CODCATEGORIA
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_empleado") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_empleado", 
		strategy = "com.idat.springboot.app.model.entity.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR
			
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "E"),  // EL PREFIJO SERA E
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d") // LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )
	})
	@Size(max=5) // TAMAÑO MAXIMO 5
	private String codigo; // ATRIBUTO CODIGO
	
	@Column(name="Nombre") // RENOMBRE DE LA COLUMNA POR NOMBRE
	@NotBlank(message="No olvides escribir el Nombre") // MENSAJE POR SI NO SE COMPLETA EL CAMPO
	@Size(min=3, max=50) // TAMAÑO MINIMO 3 Y MAXIMO 50
	private String nombre; // ATRIBUTO NOMBRE
	
	@Column(name="Apellido") // RENOMBRE DE LA COLUMNA POR APELLIDO
	@NotBlank(message="No olvides escribir el Apellido") // MENSAJE POR SI NO SE COMPLETA EL CAMPO
	@Size(min=3, max=50) // TAMAÑO MINIMO 3 Y MAXIMO 50
	private String apellido; // ATRIBUTO APELLIDO
	
	@Column(name="FechaNacimiento") // RENOMBRE DE LA COLUMNA POR FECHANACIMIENTO
	@Temporal(TemporalType.DATE)  // DETERMINAMOS QUE ALMACENA UNA FECHA
	@DateTimeFormat(pattern = "dd-MM-yyyy") // FORMATO DE FECHA
	@Past // VALIDAMOS QUE LA FECHA SEA PASADA
	private Date nacimiento; // ATRIBUTO NACIMIENTO
	
	@Column(name="FechaContrato") // RENOMBRE DE LA COLUMNA POR FECHACONTRATO
	@Temporal(TemporalType.DATE)  // DETERMINAMOS QUE ALMACENA UNA FECHA
	@DateTimeFormat(pattern = "dd-MM-yyyy") // FORMATO DE FECHA
	@FutureOrPresent // VALIDAMOS QUE LA FECHA SEA HOY O EN UN FUTURO
	private Date contrato; // ATRIBUTO CONTRATO
	
	@Column(name="Correo") // RENOMBRE DE LA COLUMNA POR CORREO
	@Email(message="Ingrese un correo válido") // VALIDACION PARA CONFIRMAR QUE SEA UN CORREO
	@Pattern(regexp=".+@.+\\..+", message="Ingrese un correo válido") // VALIDACION PARA EL FORMATO DEL CORREO
	private String correo; // ATRIBUTO CORREO
	
	@Column(name="DNI") // RENOMBRE DE LA COLUMNA POR DNI
	@NotBlank(message="No olvides escribir el DNI") // MENSAJE POR SI NO SE COMPLETA EL CAMPO
	@Size(min=8, max=8) // TAMAÑO MINIMO 8 Y MAXIMO 8
	private String dni; // ATRIBUTO DNI
	
	@Column(name="Usuario") // RENOMBRE DE LA COLUMNA POR USUARIO
	@Size(max=10) // TAMAÑO MAXIMO 10
	private String usuario; // ATRIBUTO USUARIO
	
	@Column(name="Contraseña") // RENOMBRE DE LA COLUMNA POR CONTRASEÑA
	@Size(max=10) // TAMAÑO MAXIMO 10
	private String contraseña; // ATRIBUTO CONTRASEÑA
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // RELACION UNO A MUCHOS
	@JoinColumn(name="codigo_empleado") // JOIN COLUM PARA EL FK
	private List<Cargo> cargos; // ATRIBUTO CARGOS

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

	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public Date getContrato() {
		return contrato;
	}

	public void setContrato(Date contrato) {
		this.contrato = contrato;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
}
