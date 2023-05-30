package br.com.minhalista.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.minhalista.dto.ItemDTO;
import br.com.minhalista.services.ItemService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/itens")
public class ItemResource {

	@Autowired
	private ItemService service;

	@GetMapping
	public ResponseEntity<List<ItemDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ItemDTO> findById(@PathVariable Integer id) {
		ItemDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<ItemDTO> create(@Valid @RequestBody ItemDTO chamadoDTO) {
		ItemDTO obj = service.create(chamadoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ItemDTO> update(@PathVariable Integer id, @Valid @RequestBody ItemDTO chamadoDTO) {
		ItemDTO obj = service.update(id, chamadoDTO);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ItemDTO> update(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
