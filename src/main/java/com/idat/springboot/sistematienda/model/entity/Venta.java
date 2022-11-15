package com.idat.springboot.sistematienda.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

// CREACION DE LA ENTIDAD Y TABLA VENTA
@Entity
@Table(name="Venta")
public class Venta {
	
	@Id // PRIMARY KEY
	@Column(name="CodVenta") // RENOMBRE DE LA COLUMNA POR CODPRODUCTOR
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generador_venta") // GENERADOR PARA EL CODIGO
	@GenericGenerator(name="generador_venta", 
		strategy = "com.idat.springboot.app.model.entity.GeneradorCodigo", parameters= { // IMPORTAMOS LA CLASE DEL GENERADOR
			
			@Parameter(name = GeneradorCodigo.INCREMENT_PARAM, value = "1"),  // EMPEZARA EN EL NUMERO 1 Y AVANZARA DE UNO EN UNO
			@Parameter(name = GeneradorCodigo.VALUE_PREFIX_PARAMETER, value = "V"),  // EL PREFIJO SERA V
			@Parameter(name = GeneradorCodigo.NUMBER_FORMAT_PARAMETER, value = "%04d") // LOS DIGITOS SERAN 4 ( MAXIMO = 9999 )
	})
	@Size(max=5) // TAMAÑO MAXIMO 5
	private String codigo; // ATRIBUTO CODIGO
	
	@Column(name="CodProducto") // RENOMBRE DE LA COLUMNA POR CODPRODUCTO
	private String producto; // ATRIBUTO PRODUCTO
	
	@Column(name="CodEmpleado") // RENOMBRE DE LA COLUMNA POR CODEMPLEADO
	private String empleado; // ATRIBUTO EMPLEADO
	
	@Column(name="CodCliente") // RENOMBRE DE LA COLUMNA POR CODCLIENTE
	private String cliente; // ATRIBUTO CLIENTE
	
	@Column(name="CodPago") // RENOMBRE DE LA COLUMNA POR CODPAGO
	private String pago; // ATRIBUTO PAGO
	
	@Column(name="Fecha") // RENOMBRE DE LA COLUMNA POR FECHA
	private String fecha; // ATRIBUTO FECHA

	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
	private Set<ProductoVendido> productos;
	
	public Venta() {
		this.fecha = Utiles.obtenerFechaYHoraActual();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Float getTotal() {
		Float total = 0f;
		for (ProductoVendido productoVendido: this.productos) {
			total += productoVendido.getTotal();
		}
		return total;
	}
	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getPago() {
		return pago;
	}

	public void setPago(String pago) {
		this.pago = pago;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Set<ProductoVendido> getProductos() {
		return productos;
	}

	public void setProductos(Set<ProductoVendido> productos) {
		this.productos = productos;
	}
	
	
}
