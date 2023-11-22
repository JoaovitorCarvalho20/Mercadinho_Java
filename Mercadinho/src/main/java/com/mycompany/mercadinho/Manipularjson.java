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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Manipularjson {
    
    private static Gson gson;
    
    /**
     * Metodo criada para transoformar a lista de funcionarios no formato JSON e salvar
     * em um arquivo externo, salvando as informações.
     * @param p
     */
    public static final void Escreverfuncionario (List<Funcionario> funcionarios)
    {
        File funcionarioFile = new File("C:\\Users\\joaov\\OneDrive\\Documentos\\NetBeansProjects\\mavenproject1\\Mercadinho_Java\\Mercadinho\\src\\main\\java\\ArquivosJson\\funcionario.json");
        
        gson = new Gson();
       
        System.out.println(gson.toJson(funcionarios));
        
        String json = gson.toJson(funcionarios);
        
      
        try (FileWriter writer = new FileWriter(funcionarioFile)) 
        {
            gson.toJson(funcionarios, writer);
            System.out.println("Objeto convertido para JSON e salvo");
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo criada para recuperar os dados salvos no arquivo JSON e os carregar 
     * em uma lista de funcionarios.
     * @return listaProdutos
     */
    public static final List<Funcionario> LerFuncionario()
    {
        Gson gson = new Gson();
        
        try(FileReader reader = new FileReader("C:\\Users\\joaov\\OneDrive\\Documentos\\NetBeansProjects\\mavenproject1\\Mercadinho_Java\\Mercadinho\\src\\main\\java\\ArquivosJson\\funcionario.json")){
            Funcionario[] funcionarios = gson.fromJson(reader, Funcionario[].class);
           
            List<Funcionario> listafuncionarios = Arrays.asList(funcionarios); 
            
            return listafuncionarios;
       
        }catch(IOException e){
            e.printStackTrace();      
            return null;
        }
    }
   
    
}