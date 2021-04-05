package br.com.testepan.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MesorregiaoDTO {

    private String id;
    private String nome;

    @JsonProperty("UF")
    private UFDTO UF;


}
