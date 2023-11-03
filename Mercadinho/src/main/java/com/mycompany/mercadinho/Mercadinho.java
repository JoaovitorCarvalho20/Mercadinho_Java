package com.mycompany.mercadinho;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mercadinho {

    public static void main(String[] args) {
        Estoque estoque = new Estoque();
        List<Venda> vendas = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<Funcionario> funcionarios = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int nextVendaId = 1;

        while (true) {
            System.out.println("______________________________________________");
            System.out.println("Sistema de Mercadinho");
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

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("Produtos disponíveis no estoque:");
                    estoque.exibirEstoque();

                    System.out.print("Digite o ID do produto a ser vendido: ");
                    int produtoId = scanner.nextInt();
                    scanner.nextLine();

                    Produto produtoAVender = encontrarProdutoPorID(estoque, produtoId);
                    if (produtoAVender != null) {
                        System.out.print("Digite a quantidade a ser vendida: ");
                        int quantidadeAVender = scanner.nextInt();
                        scanner.nextLine();

                        int idVenda = nextVendaId;
                        VendaItem item = new VendaItem(produtoAVender, quantidadeAVender);
                        Venda venda = new Venda();
                        venda.adicionarItem(item);
                        venda.realizarVenda(estoque);
                        vendas.add(venda);

                        System.out.println("Venda realizada com sucesso! ID da venda: " + idVenda);

                        nextVendaId++;
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 2:
                    System.out.println("Digite o ID da venda a ser cancelada: ");
                    int idVendaCancelar = scanner.nextInt();
                    scanner.nextLine();

                    Venda vendaParaCancelar = encontrarVendaPorID(vendas, idVendaCancelar);

                    if (vendaParaCancelar != null) {
                        int quantidadeCancelada = vendaParaCancelar.getQuantidadeTotal();
                        boolean canceladaComSucesso = vendaParaCancelar.cancelarVenda(estoque, quantidadeCancelada);

                        if (canceladaComSucesso) {
                            System.out.println("Venda cancelada com sucesso!");
                            System.out.println(quantidadeCancelada + " unidades retornadas ao estoque.");
                            vendas.remove(vendaParaCancelar);
                        } else {
                            System.out.println("Erro ao cancelar a venda. Verifique o estoque.");
                        }
                    } else {
                        System.out.println("Venda não encontrada.");
                    }
                    break;

                case 3:
                    System.out.println("Produtos disponíveis no estoque:");
                    estoque.exibirEstoque();
                    break;

                case 4:
                    System.out.print("Digite o ano do relatório fiscal: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();
  
                    GerenciamentoFiscal gerenciamentoFiscal = new GerenciamentoFiscal(vendas);
                    double vendasAnuais = gerenciamentoFiscal.calcularVendasAnuais(ano);
                    System.out.println("Vendas anuais em " + ano + ": R$" + vendasAnuais);
                    break;

                case 5:
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

                    boolean clienteExistente = verificarCliente(clientes, cpfCliente);

                    if (clienteExistente) {
                        System.out.println("Cliente já existe no sistema.");
                    } else {
                        Cliente novoCliente = new Cliente(nomeCliente, telefoneCliente, enderecoCliente, cpfCliente, emailCliente);
                        clientes.add(novoCliente);
                        System.out.println("Cliente cadastrado com sucesso!");
                    }
                    break;

                case 6:
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

                        Produto novoProduto = new Produto(nomeProduto, precoProduto);
                        estoque.adicionarProduto(novoProduto, quantidadeInicial);
                        System.out.println("Produto adicionado ao estoque com sucesso!");
                    }
                    break;

                case 8:
                    System.out.println("Saindo do sistema.");
                    scanner.close();
                    System.exit(0);
                    break;

                case 9:
                    Funcionario funcionario = criarFuncionario();
                    funcionarios.add(funcionario);
                    System.out.println("Funcionário criado com sucesso!");
                    break;

                case 10:
                    System.out.print("Digite o ID do funcionário a ser editado: ");
                    String idEditar = scanner.nextLine();
                    Funcionario funcionarioParaEditar = encontrarFuncionarioPorID(funcionarios, idEditar);
                    if (funcionarioParaEditar != null) {
                        editarCredenciais(funcionarioParaEditar);
                        System.out.println("Credenciais do funcionário editadas com sucesso!");
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    break;

                case 11:
                    System.out.println("Editar Produto");
                    System.out.print("Digite o ID do produto a ser editado: ");
                    int produtoEditarId = scanner.nextInt();
                    scanner.nextLine();

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

    private static Funcionario encontrarFuncionarioPorID(List<Funcionario> funcionarios, String id) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId().equals(id)) {
                return funcionario;
            }
        }
        return null;
    }

    private static Produto encontrarProdutoPorID(Estoque estoque, int produtoId) {
        for (Produto produto : estoque.getProdutos()) {
            if (produto.getId() == produtoId) {
                return produto;
            }
        }
        return null;
    }

    private static Venda encontrarVendaPorID(List<Venda> vendas, int idVenda) {
        for (Venda venda : vendas) {
            if (venda.getId() == idVenda) {
                return venda;
            }
        }
        return null;
    }

    private static boolean verificarCliente(List<Cliente> clientes, String cpfVerificar) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpfVerificar)) {
                return true;
            }
        }
        return false;
    }
}
