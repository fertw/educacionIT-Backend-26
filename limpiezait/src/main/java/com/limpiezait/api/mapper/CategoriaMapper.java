package com.limpiezait.api.mapper;

import com.limpiezait.api.dto.CategoriaDTO;
import com.limpiezait.api.model.Categoria;

public class CategoriaMapper {
	
	public static CategoriaDTO toDTO(Categoria categoria) {
		if (categoria == null) {
			return null;
		}
		
		return new CategoriaDTO(
				categoria.getId(),
				categoria.getNombre()
		);
	}

	public static Categoria toEntity(CategoriaDTO categoriaDTO) {
		if (categoriaDTO == null) {
			return null;
		}
		
		Categoria categoriaEntity = new Categoria();
		categoriaEntity.setId(categoriaDTO.getId());
		categoriaEntity.setNombre(categoriaDTO.getNombre());
		
		return categoriaEntity;
	}

}
