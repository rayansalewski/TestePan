package br.com.testepan.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MicrorregiaoDTO {

    private String id;
    private String nome;
    private MesorregiaoDTO mesorregiao;

}
