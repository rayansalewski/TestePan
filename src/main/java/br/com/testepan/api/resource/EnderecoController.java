package br.com.testepan.api.resource;

import br.com.testepan.api.dto.EnderecoDTO;
import br.com.testepan.api.dto.EstadoDTO;
import br.com.testepan.api.dto.MunicipiosDTO;
import br.com.testepan.service.CepService;
import br.com.testepan.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private CepService cepService;

	@GetMapping(value = "/estados")
	public List<EstadoDTO> listaEstados() {
		return estadoService.listaEstados();
	}

	@GetMapping(value = "/estados/{uf}")
	public List<MunicipiosDTO> encontraMunicipiosPelaUF(@PathVariable("uf") String uf) {
		return estadoService.encontraMunicipiosPelaUF(uf);
	}

	@GetMapping(value = "/{cep}")
	public EnderecoDTO cep(@PathVariable("cep") String cep) {
		return cepService.getCep(cep);
	}

}
