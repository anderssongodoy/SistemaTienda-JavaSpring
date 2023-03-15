package com.idat.springboot.sistematienda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

// CREACION DE LA ENTIDAD Y TABLA PRODUCTOVENDIDO
@Entity
@Table(name="ProductoVendido")
public class ProductoVendido {

	@Id // PRIMARY KEY
	@Column(name="CodProductoVendido") // RENOMBRE DE LA COLUMNA POR CODPRODUCTOR
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_productovendido") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_productovendido", 
		strategy = "com.idat.springboot.sistematienda.entity.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR
			
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "PV"),  // EL PREFIJO SERA PV
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d") // LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )
	})
	@Size(max=6) // TAMAÑO MAXIMO 6
	private String codigo; // ATRIBUTO CODIGO
	
	@Column(name="Cantidad") // RENOMBRE DE LA COLUMNA POR CANTIDAD
	@Min(1) // CANTIDAD MINIMO 1
	@Max(999) // CANTIDAD MAXIMA 999
	private int cantidad; // ATRIBUTO CANTIDAD
	
	@Column(name="Precio") // RENOMBRE DE LA COLUMNA POR PRECIO
	private Float precio; // ATRIBUTO PRECIO
	
	@Column(name="Descripcion") // RENOMBRE DE LA COLUMNA POR DESCRIPCION
	@Size(max=100) // TAMAÑO MAXIMO 100
	private String descripcion; // ATRIBUTO DESCRIPCION
	
	@ManyToOne
	@JoinColumn(name="CodVenta") // RENOMBRE DE LA COLUMNA POR CODVENTA
	private Venta venta; // ATRIBUTO VENTA

	public ProductoVendido(int cantidad, Float precio, String descripcion, Venta venta) {
		this.cantidad = cantidad;
		this.precio = precio;
		this.descripcion = descripcion;
		this.venta = venta;
	}
	
	public ProductoVendido() {
		
	}
	
	public Float getTotal() {
		return this.cantidad * this.precio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	
}
