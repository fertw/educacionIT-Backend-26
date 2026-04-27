package com.limpiezait.api.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.limpiezait.api.model.Producto;

@Repository
public class ProductoRepository {
	
	private ArrayList<Producto> productos= new ArrayList<Producto>();
	
	public void agregarProducto(Producto producto) {
		this.productos.add(producto);
	}
	
	public ArrayList<Producto> getProductos() {
		return productos;
	}
	
	public void eliminarProducto(Long id) {
		this.productos.removeIf(producto -> producto.getId().equals(id));
	}

}
