package br.com.testepan.service;

import br.com.testepan.api.dto.ProdutoDTO;
import br.com.testepan.api.dto.utils.DTOUtils;
import br.com.testepan.api.exception.ResourceNotFoundException;
import br.com.testepan.model.entity.Produto;
import br.com.testepan.model.repository.ProdutoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getProdutos(){
        return produtoRepository.findAll();
    }

    public Produto getProdutoById(Long id) {
        log.info("Buscando produto por id: {}", id);
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        log.info("produto de id: {} encontrado", id);
        return produto;
    }

    public Produto adicionaNovoProduto(ProdutoDTO produto){
        log.info("Adicionando novo produto");

        if(produtoRepository.existsByNome(produto.getNome())){
            throw new ResourceNotFoundException("Produto com nome ja cadastrado");
        }

        Produto entity = DTOUtils.map(produto, Produto.class);
        Produto produtoSave = produtoRepository.save(entity);
        log.info("Adicionado novo produto");
        return produtoSave;
    }

    public void removeProduto(Long id) {
        log.info("Removendo produto");
        Produto produto = getProdutoById(id);
        produtoRepository.delete(produto);
        log.info("Removido produto");
    }

}
