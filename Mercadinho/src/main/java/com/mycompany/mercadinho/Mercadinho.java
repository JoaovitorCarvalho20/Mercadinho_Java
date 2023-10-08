package com.mycompany.mercadinho;


public class Mercadinho {

    public static void main(String[] args) {
       Login logar=new Login();
        logar.cadastro();
        logar.verificarCadastro();
        Produto p1=new Produto("ARROZ", "A1", 14.0,1);
        Produto p2=new Produto("FEIJAO", "A", 13.9, 2);
        Produto p3=new Produto("arroz", "A1", 12.0, 43);
        
        Estoque estoque=new Estoque();
        estoque.adicionarProduto(p2, 45);
        estoque.adicionarProduto(p1, 34);
        

        Venda venda =new Venda();
        venda.realizarVenda(2, p3, 5);
        estoque.exibirEstoque();
          
    }
}
