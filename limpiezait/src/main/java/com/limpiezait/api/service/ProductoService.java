package com.limpiezait.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limpiezait.api.model.Producto;
import com.limpiezait.api.repository.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	public void eliminarProducto(Producto producto) {
		productoRepository.eliminarProducto(producto);
	}
	
	public Producto agregarProducto(Producto producto) {
		productoRepository.agregarProducto(producto);
		return producto;
	}
	
	public List<Producto> getProductos() {
		return productoRepository.getProductos();
	}
	
	public Producto getProductoById(Long id) {
		return productoRepository.getProductos().stream().filter(producto -> producto.getId().equals(id)).findFirst().orElse(null);
	}
	

	

}
