package br.com.testepan.api.resource;

import br.com.testepan.api.builder.ClienteBuilder;
import br.com.testepan.api.exception.ResourceNotFoundException;
import br.com.testepan.model.entity.Cliente;
import br.com.testepan.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = ClienteController.class)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class ClienteControllerTest {

    static String CLIENTES_API = "/clientes";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClienteService service;

    @Test
    @DisplayName("Deve consultar todos os clientes")
    public void deveConsultaClientes() throws Exception {

        Cliente cliente = ClienteBuilder.clienteValido().build();

        ArgumentCaptor<Cliente> clienteArgumentCaptor = ArgumentCaptor.forClass(Cliente.class);

        BDDMockito.given(service.encontraTodosOsClientes()).willReturn(Arrays.asList(cliente));

        mvc.perform(MockMvcRequestBuilders.get(CLIENTES_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cpf").value(cliente.getCpf()));

    }

    @Test
    @DisplayName("Deve criar um novo Cliente")
    public void deveCriarCliente() throws Exception {

        Cliente cliente = ClienteBuilder.clienteValido().build();

        BDDMockito.given(service.salvarCliente(Mockito.any(Cliente.class))).willReturn(cliente);

        String json = new ObjectMapper().writeValueAsString(cliente);

        mvc.perform(MockMvcRequestBuilders.post(CLIENTES_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
        .andExpect(jsonPath("cpf").value("34398176870"))
        .andExpect(jsonPath("nome").value("Rayan Salewski"));

    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficiente para criação do cliente.")
    public void createInvalidClienteTest() throws Exception {

        String json = new ObjectMapper().writeValueAsString(new Cliente());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(CLIENTES_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect( status().isBadRequest() )
                .andExpect( jsonPath("$[0].campo").value("nome"))
                .andExpect(jsonPath("$[0].descricao").value("Nome e obrigatorio"));
    }

    @Test
    @DisplayName("Deve retornar resource not found quando o cliente procurado não existir")
    public void bookNotFoundTest() throws Exception {

        String cpfInvalido = "12345678901";

        BDDMockito.given( service.buscarPorCPF(Mockito.anyString())).willThrow(ResourceNotFoundException.class);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(CLIENTES_API.concat("/" + cpfInvalido))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isNotFound());
    }

}
