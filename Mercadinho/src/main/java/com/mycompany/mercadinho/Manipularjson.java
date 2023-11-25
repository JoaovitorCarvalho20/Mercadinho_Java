/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mercadinho;

/**
 *
 * @author joaov
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Manipularjson {

    private static Gson gson;

    /**
     * Metodo criada para transoformar a lista de funcionarios no formato JSON e
     * salvar em um arquivo externo, salvando as informações.
     *
     * @param p
     */
    public static final void Escreverfuncionario(List<Funcionario> funcionarios) {
        File funcionarioFile = new File("C:\\Users\\joaov\\OneDrive\\Documentos\\NetBeansProjects\\mavenproject1\\Mercadinho_Java\\Mercadinho\\src\\main\\java\\ArquivosJson\\Funcionario.json");

        gson = new Gson();

        System.out.println(gson.toJson(funcionarios));

        String json = gson.toJson(funcionarios);

        try (FileWriter writer = new FileWriter(funcionarioFile)) {
            gson.toJson(funcionarios, writer);
            System.out.println("Objeto convertido para JSON e salvo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo criada para recuperar os dados salvos no arquivo JSON e os
     * carregar em uma lista de funcionarios.
     *
     * @return listaProdutos
     */
    public static final List<Funcionario> LerFuncionario() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("C:\\Users\\joaov\\OneDrive\\Documentos\\NetBeansProjects\\mavenproject1\\Mercadinho_Java\\Mercadinho\\src\\main\\java\\ArquivosJson\\Funcionario.json")) {
            Funcionario[] funcionarios = gson.fromJson(reader, Funcionario[].class);

            List<Funcionario> listafuncionarios = Arrays.asList(funcionarios);

            return listafuncionarios;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final void EscreverAdministrador(List<Administrador> administrador) {
        File administradorFile = new File("C:\\Users\\joaov\\OneDrive\\Documentos\\NetBeansProjects\\mavenproject1\\Mercadinho_Java\\Mercadinho\\src\\main\\java\\ArquivosJson\\Administrador.json");

        gson = new Gson();

        System.out.println(gson.toJson(administrador));

        String json = gson.toJson(administrador);

        try (FileWriter writer = new FileWriter(administradorFile)) {
            gson.toJson(administrador, writer);
            System.out.println("Objeto convertido para JSON e salvo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo criada para recuperar os dados salvos no arquivo JSON e os
     * carregar em uma lista de funcionarios.
     *
     * @return listaProdutos
     */
    public static final List<Administrador> LerAdministrador() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("C:\\Users\\joaov\\OneDrive\\Documentos\\NetBeansProjects\\mavenproject1\\Mercadinho_Java\\Mercadinho\\src\\main\\java\\ArquivosJson\\Administrador.json")) {
            Administrador[] administradores = gson.fromJson(reader, Administrador[].class);

            // Criar uma nova lista mutável para evitar UnsupportedOperationException
            List<Administrador> listaAdministradores = Arrays.asList(administradores);

            return listaAdministradores;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final void EscreverCliente(List<Cliente> clientes) {
        File clienteFile = new File("C:\\Users\\joaov\\OneDrive\\Documentos\\NetBeansProjects\\mavenproject1\\Mercadinho_Java\\Mercadinho\\src\\main\\java\\ArquivosJson\\Cliente.json");

        gson = new Gson();

        System.out.println(gson.toJson(clientes));

        String json = gson.toJson(clientes);

        try (FileWriter writer = new FileWriter(clienteFile)) {
            gson.toJson(clientes, writer);
            System.out.println("Objeto convertido para JSON e salvo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final List<Cliente> LerCliente() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("C:\\Users\\joaov\\OneDrive\\Documentos\\NetBeansProjects\\mavenproject1\\Mercadinho_Java\\Mercadinho\\src\\main\\java\\ArquivosJson\\Cliente.json")) {
            Cliente[] clientes = gson.fromJson(reader, Cliente[].class);

            // Criar uma nova lista mutável para evitar UnsupportedOperationException
            List<Cliente> listaClientes = Arrays.asList(clientes);

            return listaClientes;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

public static final void EscreverEstoque(Estoque estoque) {
    File estoqueFile = new File("C:\\Users\\joaov\\OneDrive\\Documentos\\NetBeansProjects\\mavenproject1\\Mercadinho_Java\\Mercadinho\\src\\main\\java\\ArquivosJson\\Estoque.json");

    gson = new Gson();

    JsonArray produtosArray = null;  // Inicialize como null

    // Tenta ler o conteúdo existente do arquivo, se existir
    try (FileReader fileReader = new FileReader(estoqueFile)) {
        if (fileReader.ready()) {
            produtosArray = gson.fromJson(fileReader, JsonArray.class);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Inicializa produtosArray se necessário
    if (produtosArray == null) {
        produtosArray = new JsonArray();
    }

    // Adiciona os novos dados ao array apenas se não existirem
    for (Map.Entry<Produto, Integer> produtoQuantidade : estoque.produtosQuantidade.entrySet()) {
        JsonObject produtoJson = new JsonObject();
        produtoJson.add("produto", gson.toJsonTree(produtoQuantidade.getKey()));
        produtoJson.addProperty("quantidade", produtoQuantidade.getValue());

        // Verifica se o produto já existe no array antes de adicioná-lo novamente
        boolean produtoJaExiste = false;
        for (JsonElement element : produtosArray) {
            JsonObject existingProduto = element.getAsJsonObject().getAsJsonObject("produto");
            JsonObject novoProduto = produtoJson.getAsJsonObject().getAsJsonObject("produto");

            if (existingProduto.equals(novoProduto)) {
                produtoJaExiste = true;
                break;
            }
        }

        if (!produtoJaExiste) {
            produtosArray.add(produtoJson);
        }
    }

    // Escreve o array atualizado de volta ao arquivo
    try (FileWriter writer = new FileWriter(estoqueFile)) {
        gson.toJson(produtosArray, writer);
        System.out.println("Objetos convertidos para JSON e adicionados ao estoque.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public static final Estoque LerEstoque() {
    File estoqueFile = new File("C:\\Users\\joaov\\OneDrive\\Documentos\\NetBeansProjects\\mavenproject1\\Mercadinho_Java\\Mercadinho\\src\\main\\java\\ArquivosJson\\Estoque.json");

    gson = new Gson();

    Estoque estoque = new Estoque();

    try (FileReader fileReader = new FileReader(estoqueFile)) {
        if (fileReader.ready()) {
            JsonArray produtosArray = gson.fromJson(fileReader, JsonArray.class);

            // Se produtosArray não for nulo, processa seus elementos
            if (produtosArray != null) {
                for (JsonElement element : produtosArray) {
                    JsonObject produtoJson = element.getAsJsonObject();
                    Produto produto = gson.fromJson(produtoJson.getAsJsonObject("produto"), Produto.class);
                    int quantidade = produtoJson.getAsJsonPrimitive("quantidade").getAsInt();

                    estoque.produtosQuantidade.put(produto, quantidade);
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return estoque;
}


}
