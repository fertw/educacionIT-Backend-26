package com.limpiezait.api.mapper;

import com.limpiezait.api.dto.CategoriaDTO;
import com.limpiezait.api.dto.ProductoDTO;
import com.limpiezait.api.model.Producto;

public class ProductoMapper {

	
	public static ProductoDTO toDTO(Producto producto) {
		if (producto == null) {
			return null;
		}
		
		
		return new ProductoDTO(
				producto.getId(),
				producto.getNombre(),
				producto.getPrecio(),
				producto.getDescripcion(),
				producto.getCategoria() != null ? producto.getCategoria().getNombre() : null
		);
	}

	public static Producto toEntity(ProductoDTO producto) {
		if (producto == null) {
			return null;
		}
		
		Producto productoEntity = new Producto();
		productoEntity.setId(producto.getId());
		productoEntity.setNombre(producto.getNombre());
		productoEntity.setPrecio(producto.getPrecio());
		productoEntity.setDescripcion(producto.getDescripcion());
		productoEntity.setCategoria(CategoriaMapper.toEntity(new CategoriaDTO(null, producto.getCategoria())));
		
		return productoEntity;
	}
	
}
