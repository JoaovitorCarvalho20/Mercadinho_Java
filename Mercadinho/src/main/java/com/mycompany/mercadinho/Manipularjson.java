
package com.mycompany.mercadinho;

/**
 *
 * @author joao carvalho
 */
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Q14
public class Manipularjson {

    private static Gson gson;

    /**
     * Metodo criada para transoformar a lista de funcionarios no formato JSON e
     * salvar em um arquivo externo, salvando as informações.
     *
     * 
     */
    public static final void Escreverfuncionario(List<Funcionario> funcionarios) {
        File funcionarioFile = new File("C:\\Users\\joaov\\OneDrive\\Área de Trabalho\\Mercadinho_Java\\src\\main\\java\\ArquivosJson\\Funcionario.json");

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

        try (FileReader reader = new FileReader("C:\\Users\\joaov\\OneDrive\\Área de Trabalho\\Mercadinho_Java\\src\\main\\java\\ArquivosJson\\Funcionario.json")) {
            Funcionario[] funcionarios = gson.fromJson(reader, Funcionario[].class);

            List<Funcionario> listafuncionarios = Arrays.asList(funcionarios);

            return listafuncionarios;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final void EscreverAdministrador(List<Administrador> administrador) {
        File administradorFile = new File("C:\\Users\\joaov\\OneDrive\\Área de Trabalho\\Mercadinho_Java\\src\\main\\java\\ArquivosJson\\Administrador.json");

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
     * 
     */
    public static final List<Administrador> LerAdministrador() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("C:\\Users\\joaov\\OneDrive\\Área de Trabalho\\Mercadinho_Java\\src\\main\\java\\ArquivosJson\\Administrador.json")) {
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
        File clienteFile = new File("C:\\Users\\joaov\\OneDrive\\Área de Trabalho\\Mercadinho_Java\\src\\main\\java\\ArquivosJson\\Cliente.json");

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

        try (FileReader reader = new FileReader("C:\\Users\\joaov\\OneDrive\\Área de Trabalho\\Mercadinho_Java\\src\\main\\java\\ArquivosJson\\Cliente.json")) {
            Cliente[] clientes = gson.fromJson(reader, Cliente[].class);

            // Criar uma nova lista mutável para evitar UnsupportedOperationException
            List<Cliente> listaClientes = Arrays.asList(clientes);

            return listaClientes;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final void EscreverEstoque(Produto produto) {
    File estoqueFile = new File("C:\\Users\\joaov\\OneDrive\\Área de Trabalho\\Mercadinho_Java\\src\\main\\java\\ArquivosJson\\Estoque.json");

    gson = new Gson();

    JsonArray produtosArray = new JsonArray();

    // Tenta ler o conteúdo existente do arquivo, se existir
    try (FileReader fileReader = new FileReader(estoqueFile)) {
        if (fileReader.ready()) {
            produtosArray = gson.fromJson(fileReader, JsonArray.class);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    // Adiciona o novo produto ou atualiza a quantidade se o produto já existir no array
    boolean produtoJaExiste = false;
    
    if (produtosArray.size() == 0) {
    	produto.setId(1);
        produtoJaExiste = false;
    } else {
    	for (JsonElement element : produtosArray) {
	        JsonObject existingProduto = element.getAsJsonObject();        
	        // Adiciona todos os produtos do estoque ao array
	        
        	if (existingProduto.getAsJsonPrimitive("nomeProduto").getAsString().equals(produto.getNomeProduto())
                    && existingProduto.getAsJsonPrimitive("categoria").getAsString().equals(produto.getCategoria())){
        		// Atualiza a quantidade do produto existente
                element.getAsJsonObject().addProperty("valorDoProduto", produto.getValorDoProduto());
                element.getAsJsonObject().addProperty("quantidadeInicial", produto.getQuantidadeInicial());
                produtoJaExiste = true;
                break;
            } else {
            	int idUltimoProduto = produtosArray.get(produtosArray.size() - 1).getAsJsonObject().getAsJsonPrimitive("id").getAsInt();
            	produto.setId(idUltimoProduto + 1);                
                produtoJaExiste = false;
            }
    	}
	}
    
    if (!produtoJaExiste) {
    	produtosArray.add(gson.toJsonTree(produto));
    }

    // Escreve o array atualizado de volta ao arquivo
    try (FileWriter writer = new FileWriter(estoqueFile)) {
        gson.toJson(produtosArray, writer);
        System.out.println("Objetos convertidos para JSON e adicionados ao estoque.");
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    
}
    
    public static final void EscreverEstoqueEdicao(Produto produto) {
        File estoqueFile = new File("C:\\Users\\joaov\\OneDrive\\Área de Trabalho\\Mercadinho_Java\\src\\main\\java\\ArquivosJson\\Estoque.json");

        gson = new Gson();

        JsonArray produtosArray = new JsonArray();

        // Tenta ler o conteúdo existente do arquivo, se existir
        try (FileReader fileReader = new FileReader(estoqueFile)) {
            if (fileReader.ready()) {
                produtosArray = gson.fromJson(fileReader, JsonArray.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (JsonElement element : produtosArray) {
    	    JsonObject existingProduto = element.getAsJsonObject();  
	        
        	if (existingProduto.getAsJsonPrimitive("id").getAsInt() == produto.getId()) {
        		element.getAsJsonObject().addProperty("nomeProduto", produto.getNomeProduto());
        		element.getAsJsonObject().addProperty("valorDoProduto", produto.getValorDoProduto());
        		element.getAsJsonObject().addProperty("categoria", produto.getCategoria());
                element.getAsJsonObject().addProperty("quantidadeInicial", produto.getQuantidadeInicial());
                produtosArray.add(gson.toJsonTree(produto));
                break;
            }
    	}

        // Escreve o array atualizado de volta ao arquivo
        try (FileWriter writer = new FileWriter(estoqueFile)) {
            gson.toJson(produtosArray, writer);
            System.out.println("Objetos convertidos para JSON e atualizados no estoque.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    } 
    
public static final List<Produto> LerEstoque() {
    File estoqueFile = new File("C:\\Users\\joaov\\OneDrive\\Área de Trabalho\\Mercadinho_Java\\src\\main\\java\\ArquivosJson\\Estoque.json");

    gson = new Gson();
    List<Produto>produtos=new ArrayList<>();
  
    try (FileReader fileReader = new FileReader(estoqueFile)) {
        if (fileReader.ready()) {
            JsonArray produtosArray = gson.fromJson(fileReader, JsonArray.class);
               
           

            // Se produtosArray não for nulo, processa seus elementos
            if (produtosArray != null) {
                for (JsonElement element : produtosArray) {
                    JsonObject produtoJson = element.getAsJsonObject();
                    Produto produto = gson.fromJson(produtoJson, Produto.class);
                    produtos.add(produto);
                    
                    // Exibe as informações do produto ao ler o estoque
                    System.out.println("ID: " + produto.getId() +
                            " - Nome: " + produto.getNomeProduto() +
                            " - Categoria: " + produto.getCategoria() +
                            " - Quantidade: " + produto.getQuantidadeInicial());
                 
                }
            }

            return produtos;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return null; // Retorne null se houver algum problema na leitura do arquivo
}



   
}



