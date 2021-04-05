package br.com.testepan.service;

import br.com.testepan.api.dto.EnderecoDTO;
import br.com.testepan.service.provider.ClientViaCEP;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CepService {

    private ClientViaCEP client;

    public CepService(ClientViaCEP client) {
        this.client = client;
    }

    public EnderecoDTO getCep(String cep) {
        log.info("Iniciando consulta do cep {}", cep);
        return client.buscarPorCep(cep);
    }
}
