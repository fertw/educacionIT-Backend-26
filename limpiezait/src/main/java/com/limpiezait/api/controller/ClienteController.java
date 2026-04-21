package com.limpiezait.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.limpiezait.api.model.Cliente;
import com.limpiezait.api.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	private final ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@PostMapping ()
	public Cliente agregarCliente(@RequestBody Cliente cliente) {
		return clienteService.agregarCliente(cliente);
	}
	
	@GetMapping
	public List<Cliente> getClientes() {
		return clienteService.getClientes();
	}
	
	@GetMapping("/{clienteId}/comprar/{productoId}")
	public Cliente comprarProducto(@PathVariable Long clienteId, @PathVariable Long productoId) {
		return clienteService.comprarProducto(clienteId, productoId);
	}
}
