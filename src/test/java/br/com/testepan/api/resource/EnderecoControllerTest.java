package br.com.testepan.api.resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class EnderecoControllerTest {

	@Autowired
	private MockMvc mvc;

	static String ENDERECO_API = "/enderecos";

	@Test
	@DisplayName("Deve consultar os estado com sucesso")
	public void deveConsultarEstados() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get(ENDERECO_API + "/estados")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	@DisplayName("Deve consultar os estado com UF com sucesso")
	public void deveConsultarEstadosPorUF() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get(ENDERECO_API + "/estados/RJ")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	@DisplayName("Deve consultar pelo CEP")
	public void deveConsultarCep() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get(ENDERECO_API + "/07113230").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json("{'logradouro':'Rua Pedro Francisco Costa'}"));

	}
}
