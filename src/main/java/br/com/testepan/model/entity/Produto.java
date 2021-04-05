package br.com.testepan.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_PRODUTOS")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1468389770375298054L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O Nome do produto não pode ser nulo ou vazio")
    private String nome;

    @JsonIgnore
    @ManyToOne
    private Cliente cliente;
}
