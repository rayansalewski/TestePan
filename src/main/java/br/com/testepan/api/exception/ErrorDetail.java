package br.com.testepan.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetail {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String campo;
	private String descricao;

	public ErrorDetail(String descricao){
		this.descricao = descricao;
	}

}
