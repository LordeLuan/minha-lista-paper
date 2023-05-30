package br.com.minhalista.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.minhalista.domain.Item;
import br.com.minhalista.dto.ItemDTO;
import br.com.minhalista.repositories.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repository;

	public ItemDTO findById(Integer id) {
		Item obj = repository.findById(id)
				.orElseThrow(() -> new NullPointerException("Item não encontrado! ID: " + id));
		return converToDto(obj);
	}

	public List<ItemDTO> findAll() {
		List<Item> listItem = repository.findAll();
		List<ItemDTO> listDTO = listItem.stream().map(item -> converToDto(item)).collect(Collectors.toList());
		return listDTO;
	}

	public ItemDTO create(ItemDTO itemDTO) {
		itemDTO.setId(null);
		Item objSalvo = repository.save(converToEntity(itemDTO));
		return converToDto(objSalvo);
	}

	public ItemDTO update(Integer id, ItemDTO itemDTO) {
		findById(id);
		Item objAtt = converToEntity(itemDTO);
		repository.save(objAtt);
		return converToDto(objAtt);
	}

	public void delete(Integer id) {
		ItemDTO objDto = findById(id);
		repository.delete(converToEntity(objDto));
	}

	public ItemDTO converToDto(Item item) {
		ItemDTO dto = new ItemDTO();
		dto.setId(item.getId());
		dto.setNome(item.getNome());
		dto.setQuantidade(item.getQuantidade());
		dto.setValor(item.getValor());
		return dto;
	}

	public Item converToEntity(ItemDTO itemDTO) {
		Item item = new Item();
		item.setId(itemDTO.getId());
		item.setNome(itemDTO.getNome());
		item.setQuantidade(itemDTO.getQuantidade());
		item.setValor(itemDTO.getValor());
		return item;
	}
}
