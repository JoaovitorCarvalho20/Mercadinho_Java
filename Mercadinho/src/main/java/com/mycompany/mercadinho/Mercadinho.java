package com.mycompany.mercadinho;


public class Mercadinho {

    public static void main(String[] args) {
        Login logar=new Login();
        logar.cadastro();
        logar.verificarCadastro();
        Produto p1=new Produto("ARROZ", "A1", 14.0,1);
        Produto p2=new Produto("FEIJAO", "A", 13.9, 2);
        System.out.println(p1.toString());
        Estoque estoque=new Estoque();
        estoque.adicionarProduto(p2, 45);
        estoque.adicionarProduto(p1, 34);
        estoque.exibirEstoque();

    }
}
