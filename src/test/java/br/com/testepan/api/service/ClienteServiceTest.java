package br.com.testepan.api.service;

import br.com.testepan.api.builder.ClienteBuilder;
import br.com.testepan.api.exception.ResourceNotFoundException;
import br.com.testepan.model.entity.Cliente;
import br.com.testepan.model.repository.ClienteRepository;
import br.com.testepan.service.ClienteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ClienteServiceTest {


    @Autowired
    ClienteService clienteService;

    @MockBean
    ClienteRepository repository;

    @Test
    @DisplayName("Deve salvar um Cliente")
    public void deveSalvarUmClienteTest() {
        //cenario
        Cliente cliente = ClienteBuilder.clienteValidoSemIDEndereco().build();
        when(repository.save(cliente)).thenReturn(
                ClienteBuilder.clienteValido().build()
        );

        //execucao
        Cliente clienteSalvo = clienteService.salvarCliente(cliente);

        //verificacao
        assertThat(clienteSalvo.getEndereco().getId()).isNotNull();
        assertThat(clienteSalvo.getCpf()).isEqualTo("34398176870");
        assertThat(clienteSalvo.getNome()).isEqualTo("Rayan Salewski");
        assertThat(clienteSalvo.getProdutos()).isNull();
    }

    @Test
    @DisplayName("Deve achar um cliente pelo CPF")
    public void deveFindByCPFTest() {
        //cenario
        String cpf = "34398176870";

        Cliente cliente = ClienteBuilder.clienteValido().build();
        when(repository.findById(cliente.getCpf())).thenReturn(Optional.of(cliente));

        //execucao
        Cliente clienteEncontrado = clienteService.buscarPorCPF(cpf);

        //verificacao
        assertThat(clienteEncontrado.getEndereco().getId()).isNotNull();
        assertThat(clienteEncontrado.getCpf()).isEqualTo("34398176870");
        assertThat(clienteEncontrado.getNome()).isEqualTo("Rayan Salewski");
        assertThat(clienteEncontrado.getProdutos()).isNull();
    }

    @Test
    @DisplayName("Deve retornar vazio ao obter um cliente não existente.")
    public void deveRetornarSemClienteTest(){
        String cpf = "34398176870";

        when(repository.findById(cpf)).thenReturn(Optional.empty());

        //execucao
        Cliente cliente = null;
        try {
            cliente = clienteService.buscarPorCPF(cpf);
            Assert.fail();
        } catch (ResourceNotFoundException e) {
            //verificacoes
            assertThat(e.getMessage()).isEqualTo("Cliente com cpf 34398176870 não encontrado");
        }


    }

}
