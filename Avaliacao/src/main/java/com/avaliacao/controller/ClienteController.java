package com.avaliacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avaliacao.entities.Cliente;
import com.avaliacao.service.ClienteService;

@RestController
@RequestMapping("/Cliente")
public class ClienteController {
	private final ClienteService ClienteService;

	@Autowired
	public ClienteController(ClienteService ClienteService) {
		this.ClienteService = ClienteService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
		Cliente Cliente = ClienteService.getClienteById(id);
		if (Cliente != null) {
			return ResponseEntity.ok(Cliente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Cliente>> getAllCliente() {
		List<Cliente> Cliente = ClienteService.getAllCliente();
		return ResponseEntity.ok(Cliente);
	}

	@PostMapping("/")
	public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente Cliente) {
		Cliente criarCliente = ClienteService.salvarCliente(Cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarCliente);
	}


	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente Cliente) {
		Cliente updatedCliente = ClienteService.updateCliente(id, Cliente);
		if (updatedCliente != null) {
			return ResponseEntity.ok(updatedCliente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
		boolean deleted = ClienteService.deleteCliente(id);
		if (deleted) {
			return ResponseEntity.ok().body("O Cliente foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
