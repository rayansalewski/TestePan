package br.com.testepan.service.provider;

import br.com.testepan.api.dto.EnderecoDTO;
import br.com.testepan.api.dto.EstadoDTO;
import br.com.testepan.api.dto.MunicipiosDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Log4j2
@Component
public class ClientViaCEP implements ReginalidadeInterface{

    @Value("${url.api.viacep}")
    private String url;

    private RestTemplate restTemplate;

    public ClientViaCEP(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<EstadoDTO> buscarEstados() {
        throw new UnsupportedOperationException("Não implementado");
    }

    @Override
    public List<MunicipiosDTO> buscarMunicipioPorUF(String UF) {
        throw new UnsupportedOperationException("Não implementado");
    }

    @Override
    public EnderecoDTO buscarPorCep(String cep) {
        String uri = url + cep + "/json";
        EnderecoDTO endereco = null;

        try {
            endereco = restTemplate.getForObject(uri, EnderecoDTO.class);
        } catch (RestClientException e) {
            throw new ProviderException("Cep inexistente");
        }

        return endereco == null ? null : endereco;
    }

}
