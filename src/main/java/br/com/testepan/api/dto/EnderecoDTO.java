package br.com.testepan.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnderecoDTO {

	@JsonIgnore
	private Long id;

	@NotEmpty(message = "O Logradouro não pode ser nulo ou vazio")
	private String logradouro;

	@NotNull(message = "O Numero não pode ser nulo ou vazio")
	private Long numero;

	@NotEmpty(message = "O Bairro não pode ser nulo ou vazio")
	private String bairro;

	@NotEmpty(message = "A Cidade não pode ser nula ou vazia")
	private String cidade;

	@NotEmpty(message = "O UF não pode ser nulo ou vazio")
	private String uf;

	@NotEmpty(message = "O CEP não pode ser nulo ou vazio")
	private String cep;
	
}
