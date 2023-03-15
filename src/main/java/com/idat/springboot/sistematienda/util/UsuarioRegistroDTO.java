package com.idat.springboot.sistematienda.util;



public class UsuarioRegistroDTO {

	private String codigo;
	
	private String username;
	
	private String password;
	
	private Boolean enabled;
	
	private String empleado;
	
	private String correo;

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

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public UsuarioRegistroDTO(String codigo, String username, String password, Boolean enabled, String empleado,
			String correo) {
		super();
		this.codigo = codigo;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.empleado = empleado;
		this.correo = correo;
	}

	public UsuarioRegistroDTO(String username, String password, Boolean enabled, String empleado, String correo) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.empleado = empleado;
		this.correo = correo;
	}

	public UsuarioRegistroDTO(String username) {
		super();
		this.username = username;
	} 
	
	public UsuarioRegistroDTO() {
		
	} 
}
