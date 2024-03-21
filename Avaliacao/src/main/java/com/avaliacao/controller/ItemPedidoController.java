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

import com.avaliacao.entities.ItemPedido;
import com.avaliacao.service.ItemPedidoService;

@RestController
@RequestMapping("/ItemPedido")
public class ItemPedidoController {
	private final ItemPedidoService ItemPedidoService;

	@Autowired
	public ItemPedidoController(ItemPedidoService ItemPedidoService) {
		this.ItemPedidoService = ItemPedidoService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> getItemPedidoById(@PathVariable Long id) {
		ItemPedido ItemPedido = ItemPedidoService.getItemPedidoById(id);
		if (ItemPedido != null) {
			return ResponseEntity.ok(ItemPedido);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<ItemPedido>> getAllItemPedido() {
		List<ItemPedido> ItemPedido = ItemPedidoService.getAllItemPedido();
		return ResponseEntity.ok(ItemPedido);
	}

	@PostMapping("/")
	public ResponseEntity<ItemPedido> criarItemPedido(@RequestBody ItemPedido ItemPedido) {
		ItemPedido criarItemPedido = ItemPedidoService.salvarItemPedido(ItemPedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarItemPedido);
	}


	@PutMapping("/{id}")
	public ResponseEntity<ItemPedido> updateItemPedido(@PathVariable Long id, @RequestBody ItemPedido ItemPedido) {
		ItemPedido updatedItemPedido = ItemPedidoService.updateItemPedido(id, ItemPedido);
		if (updatedItemPedido != null) {
			return ResponseEntity.ok(updatedItemPedido);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteItemPedido(@PathVariable Long id) {
		boolean deleted = ItemPedidoService.deleteItemPedido(id);
		if (deleted) {
			return ResponseEntity.ok().body("O ItemPedido foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
