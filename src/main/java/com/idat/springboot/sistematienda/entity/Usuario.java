package com.idat.springboot.sistematienda.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="users")
public class Usuario implements Serializable{
	@Id // PRIMARY KEY
	@Column(name="CodUsuario") // RENOMBRE DE LA COLUMNA POR CODUSUARIO
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_usuario") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_usuario", 
		strategy = "com.idat.springboot.sistematienda.entity.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR
			
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "U"),  // EL PREFIJO SERA U
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d") // LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )
	})
	@Size(max=5) // TAMAÑO MAXIMO 5
	private String codigo;
	
	@Column(length=45,unique=false)
	private String username;
	
	@Column(length=60)
	private String password;
	
	private Boolean enabled;
	
	private String empleado;
	
	@Column(name="Correo") // RENOMBRE DE LA COLUMNA POR CORREO
	@Email(message="Ingrese un correo válido") // VALIDACION PARA CONFIRMAR QUE SEA UN CORREO
	@Pattern(regexp=".+@.+\\..+", message="Ingrese un correo válido") // VALIDACION PARA EL FORMATO DEL CORREO
	private String correo; // ATRIBUTO CORREO
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name="user_roles",
			joinColumns = @JoinColumn(
					name="usuario_id", referencedColumnName = "CodUsuario" ),
			inverseJoinColumns = @JoinColumn(name="rol_id", referencedColumnName = "CodRole"))
	private Collection<Role> roles;
	
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Boolean getEnabled() {
		return enabled;
	}



	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}



	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}


	

	public Usuario() {
		super();
	}

	public Usuario(String username, String password, Boolean enabled, String empleado,
			@Email(message = "Ingrese un correo válido") @Pattern(regexp = ".+@.+\\..+", message = "Ingrese un correo válido") String correo,
			Collection<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.empleado = empleado;
		this.correo = correo;
		this.roles = roles;
	}

	public Usuario(@Size(max = 5) String codigo, String username, String password, Boolean enabled, String empleado,
			@Email(message = "Ingrese un correo válido") @Pattern(regexp = ".+@.+\\..+", message = "Ingrese un correo válido") String correo,
			Collection<Role> roles) {
		super();
		this.codigo = codigo;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.empleado = empleado;
		this.correo = correo;
		this.roles = roles;
	}




	private static final long serialVersionUID=1L;
}
