package com.limpiezait.api.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	
	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private List<Producto> productosComprados = new ArrayList<Producto>();
	
	public Cliente() {
		
	}

	public Cliente(String nombre, String apellido, String email, String telefono, Long id) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Producto> getProductosComprados() {
		return productosComprados;
	}

	public void setProductosComprados(List<Producto> productosComprados) {
		this.productosComprados = productosComprados;
	}



	
	
	
	
	

}
