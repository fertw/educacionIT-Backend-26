package com.limpiezait.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.limpiezait.api.dto.ProductoDTO;
import com.limpiezait.api.mapper.ProductoMapper;
import com.limpiezait.api.model.Producto;
import com.limpiezait.api.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	public void eliminarProducto(Long id) {
		productoRepository.eliminarProducto(id);
	}

	public ProductoDTO agregarProducto(ProductoDTO producto) {
		Producto productoEntity = ProductoMapper.toEntity(producto);
		productoRepository.agregarProducto(productoEntity);
		return producto;
	}

	public List<ProductoDTO> getProductos() {
		List<Producto> productos = productoRepository.getProductos();
		return productos.stream().map(ProductoMapper::toDTO).toList();
	}

	public ProductoDTO getProductoById(Long id) {

		Producto productoResponse = productoRepository.getProductos().stream()
				.filter(producto -> producto.getId().equals(id)).findFirst().orElse(null);

		return ProductoMapper.toDTO(productoResponse);

	}

	public List<ProductoDTO> agregarProducto(List<ProductoDTO> productos) {
		List<Producto> productosEntity = productos.stream().map(ProductoMapper::toEntity).toList();
		productoRepository.agregarProducto(productosEntity);
		return productos;
		
	}

}
