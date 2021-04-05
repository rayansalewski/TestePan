package br.com.testepan.api.builder;

import br.com.testepan.model.entity.Cliente;
import br.com.testepan.model.entity.Endereco;
import br.com.testepan.model.entity.Produto;

public class ClienteBuilder {

    private Cliente cliente;

    private ClienteBuilder() {}

    public static ClienteBuilder clienteValido(){
        ClienteBuilder clienteBuilder = new ClienteBuilder();

        Endereco endereco = new Endereco().builder()
                .id(1L)
                .logradouro("Avenida Italo Brasileiro Piva")
                .numero(31L).bairro("Picanco")
                .cidade("Guarulhos").cep("07080020")
                .uf("SP")
                .build();

        clienteBuilder.cliente = new Cliente().builder()
                .cpf("34398176870")
                .nome("Rayan Salewski")
                .email("rayansalewski@hotmail.com")
                .endereco(endereco)
                .build();

        return clienteBuilder;
    }

    public static ClienteBuilder clienteValidoSemIDEndereco(){
        ClienteBuilder clienteBuilder = new ClienteBuilder();

        Endereco endereco = new Endereco().builder()
                .logradouro("Avenida Italo Brasileiro Piva")
                .numero(31L).bairro("Picanco")
                .cidade("Guarulhos").cep("07080020")
                .uf("SP")
                .build();

        clienteBuilder.cliente = new Cliente().builder()
                .cpf("34398176870")
                .nome("Rayan Salewski")
                .email("rayansalewski@hotmail.com")
                .endereco(endereco)
                .build();

        return clienteBuilder;
    }


    public static ClienteBuilder clienteSemEndereco(){
        ClienteBuilder clienteBuilder = new ClienteBuilder();

        clienteBuilder.cliente = new Cliente().builder()
                .cpf("34398176870")
                .nome("Rayan Salewski")
                .email("rayansalewski@hotmail.com")
                .endereco(null)
                .build();

        return clienteBuilder;
    }

    public ClienteBuilder comProduto(Produto produto){
        cliente.addProduto(produto);
        return this;
    }

    public ClienteBuilder comEndereco(Endereco endereco){
        cliente.setEndereco(endereco);
        return this;
    }

    public Cliente build(){
        return this.cliente;
    }
}
