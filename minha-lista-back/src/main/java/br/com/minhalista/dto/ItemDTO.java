package br.com.minhalista.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ItemDTO {

	private Integer id;
	private String nome;
	private Integer quantidade;
	private Double valor;
	private Boolean ativo;
	private LocalDateTime dataCriacao;

}
