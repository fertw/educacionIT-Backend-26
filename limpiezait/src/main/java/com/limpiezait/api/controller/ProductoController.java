package com.limpiezait.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.limpiezait.api.dto.ProductoDTO;
import com.limpiezait.api.service.ProductoService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "ProductoController", description = "Controlador para gestionar productos de limpieza")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	// API publicas para obtener y agregar productos, cualquiera puede acceder a
	// ellas

	@GetMapping("/api/public/productos")
	@Operation(summary = "Obtener todos los productos", description = "Obtiene una lista de todos los productos de limpieza disponibles")
	public List<ProductoDTO> getProductos() {
		return productoService.getProductos();
	}

	@Operation(summary = "Obtener producto por ID", description = "Obtiene un producto de limpieza por su ID")
	@GetMapping("/api/admin/productos/{id}")
	@Parameter(name = "id", description = "ID del producto a obtener", example = "1")
	public ProductoDTO getProductoAdminById(@PathVariable Long id) {
		return productoService.getProductoById(id);
	}

	@GetMapping("/api/admin/productos")
	public List<ProductoDTO> getProductosAdmin() {
		return productoService.getProductos();
	}

	@GetMapping("/api/public/productos/{id}")
	@Hidden
	public ProductoDTO getProductoById(@PathVariable Long id) {
		return productoService.getProductoById(id);
	}

	// API protegida para agregar y eliminar productos, solo los usuarios con el rol
	// ADMIN pueden acceder a ellas

	@PostMapping("/api/public/productos")
	public ProductoDTO agregarProducto(@RequestBody ProductoDTO producto) {
		return productoService.agregarProducto(producto);
	}

	@PostMapping("/api/public/productos/lote")
	public List<ProductoDTO> agregarProductos(@RequestBody List<ProductoDTO> productos) {
		return productoService.agregarProducto(productos);
	}

	@DeleteMapping("api/admin/productos/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void eliminarProducto(@PathVariable Long id) {
		// si no tiene el ROL admin no puede eliminar el producto
		productoService.eliminarProducto(id);
	}

}
