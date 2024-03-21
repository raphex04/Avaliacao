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

import com.avaliacao.entities.Fornecedor;
import com.avaliacao.service.FornecedorService;

@RestController
@RequestMapping("/Fornecedor")
public class FornecedorController {
	private final FornecedorService FornecedorService;

	@Autowired
	public FornecedorController(FornecedorService FornecedorService) {
		this.FornecedorService = FornecedorService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Fornecedor> getFornecedorById(@PathVariable Long id) {
		Fornecedor Fornecedor = FornecedorService.getFornecedorById(id);
		if (Fornecedor != null) {
			return ResponseEntity.ok(Fornecedor);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Fornecedor>> getAllFornecedor() {
		List<Fornecedor> Fornecedor = FornecedorService.getAllFornecedor();
		return ResponseEntity.ok(Fornecedor);
	}

	@PostMapping("/")
	public ResponseEntity<Fornecedor> criarFornecedor(@RequestBody Fornecedor Fornecedor) {
		Fornecedor criarFornecedor = FornecedorService.salvarFornecedor(Fornecedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarFornecedor);
	}


	@PutMapping("/{id}")
	public ResponseEntity<Fornecedor> updateFornecedor(@PathVariable Long id, @RequestBody Fornecedor Fornecedor) {
		Fornecedor updatedFornecedor = FornecedorService.updateFornecedor(id, Fornecedor);
		if (updatedFornecedor != null) {
			return ResponseEntity.ok(updatedFornecedor);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteFornecedor(@PathVariable Long id) {
		boolean deleted = FornecedorService.deleteFornecedor(id);
		if (deleted) {
			return ResponseEntity.ok().body("O Fornecedor foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
