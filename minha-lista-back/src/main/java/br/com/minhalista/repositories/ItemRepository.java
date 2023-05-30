package br.com.minhalista.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.minhalista.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
