package com.limpiezait.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.limpiezait.api.model.Producto;
import com.limpiezait.api.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {	

	@Autowired
    private ProductoService productoService;
	
	@GetMapping
	public List<Producto> getProductos() {
		return productoService.getProductos();
	}
	
	@PostMapping
	public Producto agregarProducto(@RequestBody Producto producto) {
		return productoService.agregarProducto(producto);
	}
	
	@DeleteMapping
	public void eliminarProducto(@RequestBody Producto producto) {
		productoService.eliminarProducto(producto);
	}
	
	

}
