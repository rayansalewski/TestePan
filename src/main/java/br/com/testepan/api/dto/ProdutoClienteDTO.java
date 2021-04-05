package br.com.testepan.api.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProdutoClienteDTO {
    @NotNull(message = "O ID do produto não pode ser nulo")
    private Long id;
}
