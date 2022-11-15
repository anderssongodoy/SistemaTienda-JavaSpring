package com.idat.springboot.sistematienda.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

// CREACION DE LA ENTIDAD Y TABLA PRODUCTO
@Entity
@Table (name = "Producto")
public class Producto {

	@Id // PRIMARY KEY
	@Column(name="CodProducto") // RENOMBRE DE LA COLUMNA POR CODPRODUCTOR
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_producto") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_producto", 
		strategy = "com.idat.springboot.app.model.entity.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR
			
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "P"),  // EL PREFIJO SERA P
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d") // LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )
	})
	@Size(max=5) // TAMAÑO MAXIMO 5
	private String codigo; // ATRIBUTO CODIGO
	
	@Column(name="CodProveedor") // RENOMBRE DE LA COLUMNA POR CODPROVEEDOR
	@NotBlank(message="No olvides elegir el Proveedor") // MENSAJE POR SI NO SE COMPLETA EL CAMPO
	@Size(max=5) // TAMAÑO MAXIMO 5
	private String proveedor; // ATRIBUTO PROVEEDOR
	
	@Column(name="CodCategoria") // RENOMBRE DE LA COLUMNA POR CODCATEGORIA
	@NotBlank(message="No olvides elegir la Categoria") // MENSAJE POR SI NO SE COMPLETA EL CAMPO
	@Size(max=5) // TAMAÑO MAXIMO 5
	private String categoria; // ATRIBUTO CATEGORIA
	
	@Column(name="Descripcion") // RENOMBRE DE LA COLUMNA POR CODCATEGORIA
	@NotBlank(message="No olvides escribir la Descripcion") // MENSAJE POR SI NO SE COMPLETA EL CAMPO
	@Size(min=5, max=50) // TAMAÑO MAXIMO 50
	private String descripcion; // ATRIBUTO DESCRIPCION
	
	@Column(name="PrecioCosto") // RENOMBRE DE LA COLUMNA POR PRECIOCOSTO
	@NotBlank(message="No olvides escribir el Precio del Costo") // MESANJE POR SI NO SE COMPLETA EL CAMPO
	@Digits(integer=3, fraction=2) // TAMAÑO MAXIMO ENTERO 3 Y MAXIMO FRACCION 2
	private float costo; // ATRIBUTO COSTO
	
	@Column(name="PrecioVenta") // RENOMBRE DE LA COLUMNA POR PRECIOVENTA
	@Digits(integer=4, fraction=2) // TAMAÑO MAXIMO ENTERO 4 Y MAXIMO FRACCION 2
	private float venta; // ATRIBUTO VENTA
	
	@Column(name="StockMinimo") // RENOMBRE DE LA COLUMNA POR STOCKMINIMO
	@Min(2) // CANTIDAD MINIMO 2
	@Max(999) // CANTIDAD MAXIMA 999
	private int minimo; // ATRIBUTO MINIMO
	
	@Column(name="StockActual") // RENOMBRE DE LA COLUMNA POR STOCKACTUAL
	@Min(0) // CANTIDAD MINIMA 0
	@Max(999) // CANTIDAD MAXIMA 999
	private int actual; // ATRIBUTO ACTUAL
	
	@Column(name="Foto") // RENOMBRE DE LA COLUMNA POR FOTO
	private String foto; // ATRIBUTO FOTO
	
	
	
// GETTERS AND SETTERS POR CADA ATRIBUTO

	public Producto(String codigo, String proveedor, String categoria, String descripcion, float costo, 
			float venta, int minimo, int actual,
			String foto) {
		this.codigo = codigo;
		this.proveedor = proveedor;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.costo = costo;
		this.venta = venta;
		this.minimo = minimo;
		this.actual = actual;
		this.foto = foto;
	}

	public Producto(String proveedor, String categoria, String descripcion, float costo, 
			float venta, int minimo, int actual,
		String foto) {
	this.proveedor = proveedor;
	this.categoria = categoria;
	this.descripcion = descripcion;
	this.costo = costo;
	this.venta = venta;
	this.minimo = minimo;
	this.actual = actual;
	this.foto = foto;
}

	public Producto(@NotBlank(message = "No olvides escribir la Descripcion") @Size(min = 5, max = 50) String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Producto() {
		
	}

	public boolean sinStock() {
		return this.actual <= 0;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public float getVenta() {
		return venta;
	}

	public void setVenta(float venta) {
		this.venta = venta;
	}

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public int getActual() {
		return actual;
	}

	public void setActual(int actual) {
		this.actual = actual;
	}

	public void restarStock(int actual) {
		this.actual -= actual;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
}
