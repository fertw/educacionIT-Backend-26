package com.limpiezait.api.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.limpiezait.api.model.Cliente;

@Repository
public class ClienteRepository {
	
	private final List<Cliente> clientes = new ArrayList<>();
	
	public void agregarCliente(Cliente cliente) {
		this.clientes.add(cliente);
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public Optional<Cliente> getClienteById(Long id) {
		return clientes.stream().filter(cliente -> cliente.getId().equals(id)).findFirst();
	}
	
	public void eliminarCliente(Cliente cliente) {
		this.clientes.remove(cliente);
	}

}
