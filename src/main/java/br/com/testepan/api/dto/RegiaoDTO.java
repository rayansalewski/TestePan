package br.com.testepan.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegiaoDTO {

    private String id;
    private String sigla;
    private String nome;

}
