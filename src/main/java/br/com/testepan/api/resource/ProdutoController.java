package br.com.testepan.api.resource;

import br.com.testepan.api.dto.ProdutoDTO;
import br.com.testepan.model.entity.Produto;
import br.com.testepan.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public List<Produto> listarProdutos() {
		return produtoService.getProdutos();
	}

	@GetMapping(value = "/{id}")
	public Produto listarProdutoPorID(@PathVariable(value = "id") Long id) {
		return produtoService.getProdutoById(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Produto adicionaNovoProduto(@Valid @RequestBody ProdutoDTO produto) {
		return produtoService.adicionaNovoProduto(produto);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}")
	public void removeProduto(@PathVariable(value = "id") Long id) {
		produtoService.removeProduto(id);
	}
    
}
