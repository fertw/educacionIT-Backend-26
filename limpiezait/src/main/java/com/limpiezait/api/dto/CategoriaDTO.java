package com.limpiezait.api.dto;

public class CategoriaDTO {
	
	private Long id;
	private String nombre;
	
	public CategoriaDTO() {
		
	}
	
	public CategoriaDTO(Long id, String nombre) {
		this.setId(id);
		this.setNombre(nombre);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
