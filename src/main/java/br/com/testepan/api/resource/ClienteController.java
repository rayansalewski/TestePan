package br.com.testepan.api.resource;

import br.com.testepan.api.dto.EnderecoDTO;
import br.com.testepan.api.dto.ProdutoClienteDTO;
import br.com.testepan.model.entity.Cliente;
import br.com.testepan.model.entity.Endereco;
import br.com.testepan.model.entity.Produto;
import br.com.testepan.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("Cliente API")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {	

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	@ApiOperation("Busca todos os clientes")
	public List<Cliente> listaClientes() {
		return clienteService.encontraTodosOsClientes();
	}

	@ApiOperation("Busca cliente por CPF")
	@GetMapping(value = "/{cpf}")
	public Cliente listaClientesPorCPF(@PathVariable(value = "cpf") String cpf) {
		return clienteService.buscarPorCPF(cpf);
	}

	@ApiOperation("Cria novo cliente")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Cliente salvarCliente(@Valid @RequestBody Cliente cliente) {
		return clienteService.salvarCliente(cliente);
	}

	@ApiOperation("Adiciona produto ao cliente")
	@PatchMapping(value = "/{cpf}/produtos")
	public Cliente adicionarProduto(@Valid @RequestBody ProdutoClienteDTO produto, @PathVariable(value = "cpf") String cpf) {
		return clienteService.adicionaProduto(produto, cpf);
	}

	@ApiOperation("Busca produtos de um cliente por CPF")
	@GetMapping(value = "/{cpf}/produtos")
	public List<Produto> buscarProduto(@PathVariable(value = "cpf") String cpf) {
		return clienteService.getProdutos(cpf);
	}

	@ApiOperation("Atualiza endereco do cliente pelo CPF")
	@PutMapping(value = "/{cpf}/enderecos")
    public Endereco alterarEnderecoCliente(@RequestBody @Valid EnderecoDTO endereco,
											  @PathVariable(value = "cpf") String cpf) {
		return clienteService.updateEndereco(endereco, cpf);
	}

    
}
