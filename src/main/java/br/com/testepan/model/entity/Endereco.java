package br.com.testepan.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TB_ENDERECOS")
public class Endereco implements Serializable {
	private static final long serialVersionUID = -1239278368003999742L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@OneToOne(mappedBy = "endereco")
	@JsonIgnore
	private Cliente cliente;

}
