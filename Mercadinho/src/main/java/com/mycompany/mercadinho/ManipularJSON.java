/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mercadinho;

/**
 *
 * @author ianca
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.charset.StandardCharsets;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author ianca
 */
public class ManipularJSON {
    
    private static Gson gson;
    
    /**
     * Classe criada para transoformar a lista de funcionarios no formato JSON e salvar
     * em um arquivo externo, salvando as informações.
     * @param p
     */
    public static final void funcionarioToJsonFile (List<Funcionario> p)
    {
        File funcionarioFile = new File("C:\\Users\\ianca\\Documents\\New Folder (3)\\Mercadinho_Java\\Mercadinho\\src\\main\\java\\com\\mycompany\\mercadinho\\funcionario.json");
        
        gson = new Gson();
        System.out.println(gson.toJson(p));
        String json = gson.toJson(p);
        
        try (FileWriter writer = new FileWriter(funcionarioFile)) 
        {
            gson.toJson(p, writer);
            System.out.println("Objeto convertido para JSON e salvo");
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Classe criada para recuperar os dados salvos no arquivo JSON e os carregar 
     * em uma lista de funcionarios.
     * @return listaProdutos
     */
    public static final List<Funcionario> funcionarioJsonToObject()
    {
        Gson gson = new Gson();
        
        try(FileReader reader = new FileReader("C:\\Users\\ianca\\Documents\\New Folder (3)\\Mercadinho_Java\\Mercadinho\\src\\main\\java\\com\\mycompany\\mercadinho\\funcionario.json")){
            Funcionario[] funcionarios = gson.fromJson(reader, Funcionario[].class);
            List<Funcionario> listafuncionarios = Arrays.asList(funcionarios); 
            
            return listafuncionarios;
        }catch(IOException e){
            e.printStackTrace();      
            return null;
        }
    }
    
    /**
     * Classe criada para transoformar a lista clientes no formato JSON e salvar
     * em um arquivo externo, salvando as informações.
     * @param p
     */
    public static final void clienteToJsonFile (List<Cliente> p)
    {
        File clienteFile = new File("C:\\Users\\ianca\\Documents\\New Folder (3)\\Mercadinho_Java\\Mercadinho\\src\\main\\java\\com\\mycompany\\mercadinho\\cliente.json");
        
        gson = new Gson();
        System.out.println(gson.toJson(p));
        String json = gson.toJson(p);
        
        try (FileWriter writer = new FileWriter(clienteFile)) 
        {
            gson.toJson(p, writer);
            System.out.println("Objeto convertido para JSON e salvo");
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Classe criada para recuperar os dados salvos no arquivo JSON e os carregar 
     * em uma lista de cliente.
     * @return listaProdutos
     */
    public static final List<Cliente> clienteJsonToObject()
    {
        Gson gson = new Gson();
        
        try(FileReader reader = new FileReader("C:\\Users\\ianca\\Documents\\New Folder (3)\\Mercadinho_Java\\Mercadinho\\src\\main\\java\\com\\mycompany\\mercadinho\\cliente.json")){
            Cliente[] clientes = gson.fromJson(reader, Cliente[].class);
            List<Cliente> listaclientes = Arrays.asList(clientes); 
            
            return listaclientes;
        }catch(IOException e){
            e.printStackTrace();      
            return null;
        }
    }
    
    
    /**
     * Classe criada para transoformar a lista vendas no formato JSON e salvar
     * em um arquivo externo, salvando as informações.
     * @param p
     */
    public static final void vendaToJsonFile (List<Cliente> p)
    {
        File vendaFile = new File("C:\\Users\\ianca\\Documents\\New Folder (3)\\Mercadinho_Java\\Mercadinho\\src\\main\\java\\com\\mycompany\\mercadinho\\venda.json");
        
        gson = new Gson();
        System.out.println(gson.toJson(p));
        String json = gson.toJson(p);
        
        try (FileWriter writer = new FileWriter(vendaFile)) 
        {
            gson.toJson(p, writer);
            System.out.println("Objeto convertido para JSON e salvo");
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Classe criada para recuperar os dados salvos no arquivo JSON e os carregar 
     * em uma lista de vendas.
     * @return listaProdutos
     */
    public static final List<Venda> vendaJsonToObject()
    {
        Gson gson = new Gson();
        
        try(FileReader reader = new FileReader("C:\\Users\\ianca\\Documents\\New Folder (3)\\Mercadinho_Java\\Mercadinho\\src\\main\\java\\com\\mycompany\\mercadinho\\cliente.json")){
            Venda[] vendas = gson.fromJson(reader, Venda[].class);
            List<Venda> listavendas = Arrays.asList(vendas); 
            
            return listavendas;
        }catch(IOException e){
            e.printStackTrace();      
            return null;
        }
    }
    
    
}
    
    

    