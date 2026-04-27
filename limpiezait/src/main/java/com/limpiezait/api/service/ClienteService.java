package com.limpiezait.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.limpiezait.api.dto.ProductoDTO;
import com.limpiezait.api.mapper.ProductoMapper;
import com.limpiezait.api.model.Cliente;
import com.limpiezait.api.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private final ClienteRepository clienteRepository;
	private final ProductoService productoService;
	
	public ClienteService(ClienteRepository clienteRepository, ProductoService productoService) {
		this.clienteRepository = clienteRepository;
		this.productoService = productoService;
	}
	
	public void eliminarCliente(Cliente cliente) {
		clienteRepository.eliminarCliente(cliente);
	}
	
	public Cliente agregarCliente(Cliente cliente) {
		clienteRepository.agregarCliente(cliente);
		return cliente;
	}
	
	public List<Cliente> getClientes() {
		return clienteRepository.getClientes();
	}
	
	public Cliente getClienteById(Long id) {
		return clienteRepository.getClienteById(id).orElse(null);
	}

	public Cliente comprarProducto(Long clienteId, Long productoId) {
		Cliente cliente = getClienteById(clienteId);
		if (cliente != null) {
			ProductoDTO producto = productoService.getProductoById(productoId);
			if (producto != null) {
				cliente.getProductosComprados().add(ProductoMapper.toEntity(producto));
				clienteRepository.agregarCliente(cliente); // Actualizar el cliente con el nuevo producto comprado
				productoService.eliminarProducto(productoId); // Eliminar el producto del inventario
				return cliente;
			}
			return cliente;
		}
		return null;
		
	}
	
}
