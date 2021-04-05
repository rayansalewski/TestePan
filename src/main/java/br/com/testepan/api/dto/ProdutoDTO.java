package br.com.testepan.api.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ProdutoDTO {
    @NotEmpty(message = "O Nome do produto deve ser preenchido")
    private String nome;
}
