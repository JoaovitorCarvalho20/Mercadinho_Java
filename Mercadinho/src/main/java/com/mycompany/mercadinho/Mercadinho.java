package com.mycompany.mercadinho;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mercadinho {

    public static void main(String[] args) {
        // Inicialização de objetos e variáveis
        Estoque estoque = new Estoque();
        List<Venda> vendas = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<Funcionario> funcionarios = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Loop principal do programa
        while (true) {
            System.out.println("______________________________________________");
            System.out.println("Sistema de Mercadinho");
            // Exibe o menu de opções
            System.out.println("1. Realizar Venda");
            System.out.println("2. Cancelar Venda");
            System.out.println("3. Verificar Estoque");
            System.out.println("4. Gerar Relatório Fiscal");
            System.out.println("5. Cadastrar Cliente");
            System.out.println("6. Verificar Cliente");
            System.out.println("7. Adicionar Produto");
            System.out.println("8. Sair do sistema");
            System.out.println("9. Criar Credenciais de Funcionário");
            System.out.println("10. Editar Funcionário");
            System.out.println("11. Editar Produto");
            System.out.println("______________________________________________");

            // Obtém a escolha do usuário
            int choice = scanner.nextInt();
            scanner.nextLine();

            // Estrutura de switch para processar a escolha do usuário
            switch (choice) {
                case 1:
                    // Realizar Venda
                    System.out.println("Produtos disponíveis no estoque:");
                    estoque.exibirEstoque();

                    System.out.print("Digite o ID do produto a ser vendido: ");
                    int produtoId = scanner.nextInt();
                    scanner.nextLine();

                    // Encontra o produto no estoque
                    Produto produtoAVender = encontrarProdutoPorID(estoque, produtoId);
                    if (produtoAVender != null) {
                        System.out.print("Digite a quantidade a ser vendida: ");
                        int quantidadeAVender = scanner.nextInt();
                        scanner.nextLine();

                        // Cria um item de venda e uma venda, realiza a venda e a adiciona à lista de vendas
                        VendaItem item = new VendaItem(produtoAVender, quantidadeAVender);
                        Venda venda = new Venda();
                        venda.adicionarItem(item);
                       venda.realizarVenda(0, estoque, venda.getItens());
                        vendas.add(venda);

                        System.out.println("Venda realizada com sucesso!");
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 2:
                    // Cancelar Venda
                    System.out.println("Produtos disponíveis no estoque:");
                    estoque.exibirEstoque();

                    System.out.print("Digite o ID do produto a ser cancelado: ");
                    int produtoCancelarId = scanner.nextInt();
                    scanner.nextLine();

                    // Encontra o produto no estoque
                    Produto produtoACancelar = encontrarProdutoPorID(estoque, produtoCancelarId);
                    if (produtoACancelar != null) {
                        // Encontra a venda associada ao produto e a cancela
                        Venda vendaParaCancelar = encontrarVendaPorProduto(vendas, produtoACancelar);
                        if (vendaParaCancelar != null) {
                            vendaParaCancelar.cancelarVenda(estoque); // Cancela toda a venda
                            System.out.println("Venda cancelada com sucesso!");
                        } else {
                            System.out.println("Produto não encontrado nas vendas.");
                        }
                    } else {
                        System.out.println("Produto não encontrado no estoque.");
                    }
                    break;

                case 3:
                    // Verificar Estoque
                    // Exibe a lista de produtos disponíveis no estoque
                    System.out.println("Produtos disponíveis no estoque:");
                    estoque.exibirEstoque();
                    break;

                case 4:
                    // Gerar Relatório Fiscal
                    System.out.print("Digite o ano do relatório fiscal: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();

                    // Calcula as vendas anuais e exibe o valor
                    GerenciamentoFiscal gerenciamentoFiscal = new GerenciamentoFiscal(vendas);
                    double vendasAnuais = gerenciamentoFiscal.calcularVendasAnuais(ano);
                    System.out.println("Vendas anuais em " + ano + ": R$" + vendasAnuais);
                    break;

                case 5:
                    // Cadastrar Cliente
                    // Solicita informações do cliente e o cadastra
                    System.out.print("Digite o nome do cliente: ");
                    String nomeCliente = scanner.nextLine();
                    System.out.print("Digite o telefone do cliente: ");
                    String telefoneCliente = scanner.nextLine();
                    System.out.print("Digite o endereço do cliente: ");
                    String enderecoCliente = scanner.nextLine();
                    System.out.print("Digite o CPF do cliente: ");
                    String cpfCliente = scanner.nextLine();
                    System.out.print("Digite o email do cliente: ");
                    String emailCliente = scanner.nextLine();

                    // Verifique se o cliente já existe
                    boolean clienteExistente = verificarCliente(clientes, cpfCliente);

                    if (clienteExistente) {
                        System.out.println("Cliente já existe no sistema.");
                    } else {
                        // Crie um novo cliente e o adicione à lista
                        Cliente novoCliente = new Cliente(nomeCliente, telefoneCliente, enderecoCliente, cpfCliente, emailCliente);
                        clientes.add(novoCliente);
                        System.out.println("Cliente cadastrado com sucesso!");
                    }
                    break;

                case 6:
                    // Verificar Cliente
                    System.out.print("Digite o CPF do cliente a ser verificado: ");
                    String cpfVerificar = scanner.nextLine();
                    boolean clienteExiste = verificarCliente(clientes, cpfVerificar);
                    if (clienteExiste) {
                        System.out.println("Cliente encontrado no sistema.");
                    } else {
                        System.out.println("Cliente não encontrado no sistema.");
                    }
                    break;

               case 7:
    // Adicionar Produto
    boolean continuarAdicionandoProdutos = true;

    while (continuarAdicionandoProdutos) {
        System.out.print("Digite o nome do novo produto (ou pressione Esc para parar): ");
        String nomeProduto = scanner.nextLine();
        if (nomeProduto.equalsIgnoreCase("Esc")) {
            continuarAdicionandoProdutos = false;
            continue;
        }

        System.out.print("Digite o preço do produto: ");
        double precoProduto = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Digite a quantidade inicial em estoque: ");
        int quantidadeInicial = scanner.nextInt();
        scanner.nextLine();

        // Cria um novo produto e o adiciona ao estoque
        Produto novoProduto = new Produto(nomeProduto, precoProduto);
        estoque.adicionarProduto(novoProduto, quantidadeInicial);
        System.out.println("Produto adicionado ao estoque com sucesso!");
    }
    break;


                case 8:
                   //sair do menu 
                    System.out.println("Saindo do sistema.");
                    scanner.close();
                    System.exit(0);
                    break;

                case 9:
                    // Criar Funcionário
                    // Cria um novo funcionário com informações como nome, ID, senha e se é administrador ou não
                    Funcionario funcionario = criarFuncionario();
                    funcionarios.add(funcionario);
                    System.out.println("Funcionário criado com sucesso!");
                    break;

                case 10:
                    // Editar Credenciais de Funcionário
                    System.out.print("Digite o ID do funcionário a ser editado: ");
                    String idEditar = scanner.nextLine();
                    Funcionario funcionarioParaEditar = encontrarFuncionarioPorID(funcionarios, idEditar);
                    if (funcionarioParaEditar != null) {
                        // Edita as credenciais do funcionário
                        editarCredenciais(funcionarioParaEditar);
                        System.out.println("Credenciais do funcionário editadas com sucesso!");
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    break;

                case 11:
                    // Editar Produto
                    System.out.println("Editar Produto");
                    System.out.print("Digite o ID do produto a ser editado: ");
                    int produtoEditarId = scanner.nextInt();
                    scanner.nextLine();

                    // Encontra o produto no estoque e permite editar seu nome e preço
                    Produto produtoAEditar = encontrarProdutoPorID(estoque, produtoEditarId);
                    if (produtoAEditar != null) {
                        System.out.print("Digite o novo nome do produto: ");
                        String novoNomeProduto = scanner.nextLine();
                        System.out.print("Digite o novo preço do produto: ");
                        double novoPrecoProduto = scanner.nextDouble();
                        scanner.nextLine();
                        produtoAEditar.setNomeProduto(novoNomeProduto);
                        produtoAEditar.setValorDoProduto(novoPrecoProduto);
                        System.out.println("Produto editado com sucesso!");
                    } else {
                        System.out.println("Produto não encontrado no estoque.");
                    }
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    // Método para criar um novo funcionário
    private static Funcionario criarFuncionario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do funcionário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o ID do funcionário: ");
        String id = scanner.nextLine();
        System.out.print("Digite a senha do funcionário: ");
        String senha = scanner.nextLine();
        System.out.print("O funcionário é um administrador? (true/false): ");
        boolean isAdm = scanner.nextBoolean();

        if (isAdm) {
            return new Administrador(nome, id, senha);
        } else {
            return new Funcionario(nome, id, senha);
        }
    }

    // Método para editar as credenciais de um funcionário
    private static void editarCredenciais(Funcionario funcionario) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Credenciais atuais do funcionário:");
        System.out.println(funcionario);
        System.out.print("Digite o novo nome do funcionário: ");
        String novoNome = scanner.nextLine();
        System.out.print("Digite o novo ID do funcionário: ");
        String novoId = scanner.nextLine();
        System.out.print("Digite a nova senha do funcionário: ");
        String novaSenha = scanner.nextLine();

        funcionario.setNome(novoNome);
        funcionario.setId(novoId);
        funcionario.setSenha(novaSenha);
    }

    // Método auxiliar para encontrar um funcionário por ID
    private static Funcionario encontrarFuncionarioPorID(List<Funcionario> funcionarios, String id) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId().equals(id)) {
                return funcionario;
            }
        }
        return null;
    }

    // Método auxiliar para encontrar um produto por ID
    private static Produto encontrarProdutoPorID(Estoque estoque, int produtoId) {
        for (Produto produto : estoque.getProdutos()) {
            if (produto.getId() == produtoId) {
                return produto;
            }
        }
        return null;
    }

    // Método auxiliar para encontrar uma venda por produto
    private static Venda encontrarVendaPorProduto(List<Venda> vendas, Produto produto) {
        for (Venda venda : vendas) {
            for (VendaItem item : venda.getItens()) {
                if (item.getProduto().equals(produto)) {
                    return venda;
                }
            }
        }
        return null;
    }

    // Método auxiliar para verificar se um cliente já existe
    private static boolean verificarCliente(List<Cliente> clientes, String cpfVerificar) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpfVerificar)) {
                return true;
            }
        }
        return false;
    }
}
