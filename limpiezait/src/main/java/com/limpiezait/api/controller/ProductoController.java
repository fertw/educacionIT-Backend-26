package com.limpiezait.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.limpiezait.api.dto.ProductoDTO;
import com.limpiezait.api.service.ProductoService;

@RestController
public class ProductoController {	

	@Autowired
    private ProductoService productoService;
	
	
	//API publicas para obtener y agregar productos, cualquiera puede acceder a ellas
	
	
	@GetMapping("/api/public/productos")
	public List<ProductoDTO> getProductos() {
		return productoService.getProductos();
	}
	
	@GetMapping("/api/public/productos/{id}")
	public ProductoDTO getProductoById(@PathVariable Long id) {
		return productoService.getProductoById(id);
	}

	
	// API protegida para agregar y eliminar productos, solo los usuarios con el rol ADMIN pueden acceder a ellas
	
	@PostMapping("/api/admin/productos")
	@PreAuthorize("hasRole('ADMIN')")
	public ProductoDTO agregarProducto(@RequestBody ProductoDTO producto) {
		return productoService.agregarProducto(producto);
	}
	
	@DeleteMapping("api/admin/productos/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void eliminarProducto(@PathVariable Long id) {
		//si no tiene el ROL admin no puede eliminar el producto
		productoService.eliminarProducto(id);
	}
	
	

}
