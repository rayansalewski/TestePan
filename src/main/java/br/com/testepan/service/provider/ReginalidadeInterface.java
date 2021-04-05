package br.com.testepan.service.provider;

import br.com.testepan.api.dto.EnderecoDTO;
import br.com.testepan.api.dto.EstadoDTO;
import br.com.testepan.api.dto.MunicipiosDTO;

import java.util.List;

public interface ReginalidadeInterface {

    List<EstadoDTO> buscarEstados();

    List<MunicipiosDTO> buscarMunicipioPorUF(String UF);

    EnderecoDTO buscarPorCep(String cep);
}
