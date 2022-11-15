package com.idat.springboot.sistematienda.model.entity;

public class ProductoParaVender extends Producto{
	
	private int cantidad;
	
	public ProductoParaVender(String codigo, String proveedor, String categoria, String descripcion, float costo, 
			float venta, int minimo, int actual,
			String foto, int cantidad) {
		super(codigo, proveedor, categoria, descripcion, costo, venta, minimo, actual, foto);
		this.cantidad = cantidad;
	}
	
	public ProductoParaVender(String proveedor, String categoria, String descripcion, float costo, 
			float venta, int minimo, int actual,
			String foto, int cantidad) {
		super(proveedor, categoria, descripcion, costo, venta, minimo, actual, foto);
		this.cantidad = cantidad;
	}
	
	public void aumentarCantidad() {
		this.cantidad++;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public Float getTotal() {
		return this.getVenta() * this.cantidad;
	}

}
