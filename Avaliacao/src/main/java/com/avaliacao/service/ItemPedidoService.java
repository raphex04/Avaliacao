package com.avaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaliacao.entities.ItemPedido;
import com.avaliacao.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	private final ItemPedidoRepository itemPedidoRepository;

	@Autowired
	public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
		this.itemPedidoRepository = itemPedidoRepository;
	}

	public List<ItemPedido> getAllItemPedido() {
		return itemPedidoRepository.findAll();
	}

	public ItemPedido getItemPedidoById(Long id) {
		Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(id);
		return itemPedido.orElse(null);
	}

	public ItemPedido salvarItemPedido(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}

	public ItemPedido updateItemPedido(Long id, ItemPedido updatedItemPedido) {
		Optional<ItemPedido> existingItemPedido = itemPedidoRepository.findById(id);
		if (existingItemPedido.isPresent()) {
			updatedItemPedido.setId(id);
			return itemPedidoRepository.save(updatedItemPedido);
		}
		return null;
	}

	public boolean deleteItemPedido(Long id) {
		Optional<ItemPedido> existingItemPedido = itemPedidoRepository.findById(id);
		if (existingItemPedido.isPresent()) {
			itemPedidoRepository.deleteById(id);
			return true;
		}
		return false;
	}
}