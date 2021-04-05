package br.com.testepan.service.provider;

import br.com.testepan.api.dto.EnderecoDTO;
import br.com.testepan.api.dto.EstadoDTO;
import br.com.testepan.api.dto.MunicipiosDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Log4j2
@Component
public class ClientIBGE implements ReginalidadeInterface{

    @Value("${url.api.ibge}")
    private String url;

    private RestTemplateBuilder restTemplateBuilder;

    public ClientIBGE(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<EstadoDTO> buscarEstados() {
        ResponseEntity<List<EstadoDTO>> estados = null;
        try {
            estados = restTemplateBuilder.build().exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<EstadoDTO>>() {
                    });

        } catch (RestClientException e){
            throw new ProviderException("Erro ao buscar todos os estados");
        }

        return estados == null ? null : estados.getBody();
    }

    @Override
    public List<MunicipiosDTO> buscarMunicipioPorUF(String UF) {

        String uri = url + UF + "/municipios";

        ResponseEntity<List<MunicipiosDTO>> municipios = null;
        try {
            municipios = restTemplateBuilder.build()
                    .exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<MunicipiosDTO>>() {
                    });

        } catch (RestClientException e){
            throw new ProviderException("Erro ao buscar todos os estados");
        }

        return municipios == null ? null : municipios.getBody();
    }

    @Override
    public EnderecoDTO buscarPorCep(String cep) {
        throw new UnsupportedOperationException("Não implementado");
    }

}
