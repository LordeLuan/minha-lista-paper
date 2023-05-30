package br.com.minhalista.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "item")
@Data
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "Informe o nome do item a ser adicionado na lista")
//	@Max(value = 250, message = "Valor informado maior que o aceitável")
	private String nome;
	private Integer quantidade;
	private Double valor;
	private Boolean ativo;

	@CreationTimestamp
	private LocalDateTime dataCriacao;

}
