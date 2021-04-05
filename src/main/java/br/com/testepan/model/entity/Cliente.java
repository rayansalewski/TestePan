package br.com.testepan.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CLIENTES")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Size(message = "O CPF deve conter 11 caracteres", max = 11, min = 11)
	private String cpf;

	@NotEmpty(message = "Nome e obrigatorio")
	private String nome;

	@Email(message = "Email deve ser valido")
	private String email;

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Produto> produtos = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "endereco_id", unique = true)
	private Endereco endereco;

	public void addProduto(Produto produto){
		this.produtos.add(produto);
	}
}
