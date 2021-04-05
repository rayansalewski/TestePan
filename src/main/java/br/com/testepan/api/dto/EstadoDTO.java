package br.com.testepan.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EstadoDTO {

    private String id;
    private String sigla;
    private String nome;
    private RegiaoDTO regiao;


}
