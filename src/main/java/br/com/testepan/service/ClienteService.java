package br.com.testepan.service;

import br.com.testepan.api.dto.EnderecoDTO;
import br.com.testepan.api.dto.ProdutoClienteDTO;
import br.com.testepan.api.dto.utils.DTOUtils;
import br.com.testepan.api.exception.ResourceNotFoundException;
import br.com.testepan.model.entity.Cliente;
import br.com.testepan.model.entity.Endereco;
import br.com.testepan.model.entity.Produto;
import br.com.testepan.model.repository.ClienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoService produtoService;

	public List<Cliente> encontraTodosOsClientes() {
		log.info("Iniciando consulta de cliente");
		return clienteRepository.findAll();
	}

	public Cliente buscarPorCPF(String cpf) {
		log.info("Iniciando consulta de cliente por CPF");
		return clienteRepository.findById(cpf)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente com cpf " + cpf + " não encontrado"));
	}

	public Cliente salvarCliente(Cliente cliente){
		log.info("Iniciando criação de novo cliente");

		if (cliente.getProdutos() != null) {
			cliente.getProdutos().forEach(produto -> {
				produtoService.getProdutoById(produto.getId());
			});
		}

		return clienteRepository.save(cliente);
	}

	public Cliente update(Cliente cliente, String cpf){
		log.info("Atualizando cliente {}", cpf);
		buscarPorCPF(cpf);
		return clienteRepository.save(cliente);
	}

	public Cliente adicionaProduto(ProdutoClienteDTO produto, String cpf) {
		log.info("Adicionando produto ao cliente {}", cpf);
		Produto produtoRetorno = produtoService.getProdutoById(produto.getId());

		Cliente cliente = buscarPorCPF(cpf);
		cliente.addProduto(produtoRetorno);

		return salvarCliente(cliente);
	}

	public List<Produto> getProdutos(String cpf) {
		log.info("Buscando produtos do cliente {}", cpf);
		Cliente cliente = buscarPorCPF(cpf);
		return cliente.getProdutos();
	}

	public Endereco updateEndereco(EnderecoDTO endereco, String cpf) {
		log.info("Atualizando endereco do cliente {}", cpf);
		Cliente cliente = buscarPorCPF(cpf);
		endereco.setId(cliente.getEndereco().getId());
		cliente.setEndereco(DTOUtils.map(endereco, Endereco.class));
		cliente = clienteRepository.save(cliente);
		return cliente.getEndereco();
	}

}
