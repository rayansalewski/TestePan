package br.com.testepan.model.repository;

import br.com.testepan.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    boolean existsByNome(String nome);
}
