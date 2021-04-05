package br.com.testepan.service;

import br.com.testepan.api.dto.EstadoDTO;
import br.com.testepan.api.dto.MunicipiosDTO;
import br.com.testepan.service.provider.ClientIBGE;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class EstadoService {

    private ClientIBGE client;

    public EstadoService(ClientIBGE client) {
        this.client = client;
    }

    public List<EstadoDTO> listaEstados() {
        log.info("Iniciando consulta dos estados");
        List<EstadoDTO> estados = client.buscarEstados();
        return ordenaEstados(estados);
    }

    public List<MunicipiosDTO> encontraMunicipiosPelaUF(String uf) {
        log.info("Iniciando consulta dos estados por UF: {}", uf);
        return client.buscarMunicipioPorUF(uf);
    }

    private List<EstadoDTO> ordenaEstados(List<EstadoDTO> estados) {

        List<EstadoDTO> body = estados.stream().sorted(Comparator.comparing(EstadoDTO::getSigla))
                .collect(Collectors.toList());

        List<EstadoDTO> list = body.stream().filter(s1 -> s1.getSigla().equals("SP")).collect(Collectors.toList());
        list.addAll(body.stream().filter(s1 -> s1.getSigla().equals("RJ")).collect(Collectors.toList()));
        body.removeAll(list);
        body.addAll(0, list);

        log.debug("Ordenando estados da listagem {}", body);

        return body;
    }
}
