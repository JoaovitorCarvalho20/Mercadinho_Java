package com.mycompany.mercadinho;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;

public class Mercadinho {
//TESTES 

    private static Produto produtoHortifruti1 = new Produto("Maçã", 2.5, "Hortifrúti");
    private static Produto produtoHortifruti2 = new Produto("Banana", 1.8, "Hortifrúti");
    private static Produto produtoAlimento1 = new Produto("Arroz", 5.0, "Alimentos");
    private static Produto produtoAlimento2 = new Produto("Feijão", 4.0, "Alimentos");
    private static Produto produtoCongelado1 = new Produto("Pizza Congelada", 10.0, "Congelados");
    private static Produto produtoCongelado2 = new Produto("Sorvete", 8.0, "Congelados");
    private static Produto produtoBebida1 = new Produto("Refrigerante", 3.0, "Bebidas");
    private static Produto produtoBebida2 = new Produto("Suco de Laranja", 2.5, "Bebidas");
    //Lista estatica de 5 caixas
    private static List<Caixa> caixas = new ArrayList<>();
    private static List<Login> logins = new ArrayList<>();
    private static Login usuarioLogado = null;
    private static int ultimoId = 1; // Inicialize com o valor desejado

    public static void main(String[] args) {
        Estoque estoque = new Estoque();
        List<Venda> vendas = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<Funcionario> funcionarios = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int nextVendaId = 1;

        // Adicione os produtos estáticos ao estoque
        estoque.adicionarProduto(produtoHortifruti1, 20);
        estoque.adicionarProduto(produtoHortifruti2, 15);
        estoque.adicionarProduto(produtoAlimento1, 30);
        estoque.adicionarProduto(produtoAlimento2, 25);
        estoque.adicionarProduto(produtoCongelado1, 10);
        estoque.adicionarProduto(produtoCongelado2, 12);
        estoque.adicionarProduto(produtoBebida1, 40);
        estoque.adicionarProduto(produtoBebida2, 35);
       Administrador Adm = new Administrador("joao", 2, "123");
        
        while (true) {
            if (usuarioLogado == null) {
                System.out.print("Digite o nome de usuário: ");
                String nomeUsuario = scanner.nextLine();
                System.out.print("Digite a senha: ");
                String senha = scanner.nextLine();

                Login login = encontrarLoginPorNomeUsuario(nomeUsuario);
                if (login != null && login.getSenha().equals(senha)) {
                    usuarioLogado = login;
                    System.out.println("Login bem-sucedido!");
                } else {
                    System.out.println("Login falhou. Tente novamente.");
                    continue;
                }
            }

            for (int i = 1; i <= 5; i++) {
                Caixa caixa = new Caixa(i, new ArrayList<>(), new ArrayList<>(), new GerenciamentoFiscal(vendas));
                caixas.add(caixa);
            }

            System.out.print("Digite o número do caixa: ");
            int numeroCaixaEscolhido = scanner.nextInt();
            Caixa caixaAtual = encontrarCaixaPorNumero(numeroCaixaEscolhido);

            if (caixaAtual == null) {
                System.out.println("Caixa não encontrado. Saindo do sistema.");
                scanner.close();
                System.exit(0);
            }

            System.out.println("Bem-vindo ao Caixa " + numeroCaixaEscolhido);

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
                System.out.println("10.Editar Funcionário");
                System.out.println("11.Editar Produto");
                System.out.println("12.Relatórios do Caixa");
                System.out.println("______________________________________________");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Produtos disponíveis no estoque:");
                        estoque.exibirEstoque();

                        List<VendaItem> itensDaVenda = new ArrayList<>();
                        boolean continuarComprando = true;
                        double valorTotalVenda = 0.0; // Variável para rastrear o valor total da venda

                        while (continuarComprando) {
                            System.out.print("Digite o ID do produto a ser vendido (ou '0' para finalizar a compra): ");
                            String input = scanner.nextLine();

                            if ("0".equals(input)) {
                                continuarComprando = false;
                                break;
                            }

                            int produtoId = Integer.parseInt(input);
                            Produto produtoAVender = encontrarProdutoPorID(estoque, produtoId);

                            if (produtoAVender != null) {
                                System.out.print("Digite a quantidade a ser vendida: ");
                                int quantidadeAVender = scanner.nextInt();
                                scanner.nextLine();

                                VendaItem item = new VendaItem(produtoAVender, quantidadeAVender);
                                itensDaVenda.add(item);

                                double valorItem = quantidadeAVender * produtoAVender.getValorDoProduto();
                                valorTotalVenda += valorItem; // Adiciona o valor do item ao valor total da venda
                            } else {
                                System.out.println("Produto não encontrado.");
                            }
                        }

                        if (!itensDaVenda.isEmpty()) {
                            int idVenda = nextVendaId;
                            Venda venda = new Venda();

                            for (VendaItem item : itensDaVenda) {
                                venda.adicionarItem(item);
                            }

                            venda.realizarVenda(estoque);
                            vendas.add(venda);

                            int quantidadeVendida = venda.getQuantidadeTotal();

                            System.out.println("______________________________________________");
                            System.out.println("Venda realizada com sucesso! ID da venda: " + idVenda);
                            System.out.println("Produtos vendidos:");

                            for (VendaItem item : itensDaVenda) {
                                Produto produto = item.getProduto();
                                int quantidade = item.getQuantidade();
                                double valorUnitario = produto.getValorDoProduto();
                                double valorItem = quantidade * valorUnitario;
                                System.out.println(produto.getNomeProduto() + " - Quantidade: " + quantidade + " - Valor Unitário: R$" + valorUnitario + " - Valor do Item: R$" + valorItem);
                            }

                            System.out.println("Valor total da venda: R$" + valorTotalVenda);
                            System.out.println("______________________________________________");

                            nextVendaId++;
                        }

                        break;

                    case 2:
                        System.out.println("______________________________________________");
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
                        System.out.println("______________________________________________");
                        break;

                    case 3:
                        System.out.println("______________________________________________");
                        System.out.println("Produtos disponíveis no estoque:");
                        estoque.exibirEstoque();
                        System.out.println("______________________________________________");
                        break;

                    case 4:
                        System.out.println("Opções de relatório fiscal:");
                        System.out.println("1. Vendas diárias");
                        System.out.println("2. Vendas mensais");
                        System.out.println("3. Vendas anuais");
                        System.out.print("Escolha a opção de relatório: ");
                        int opcaoRelatorio = scanner.nextInt();
                        scanner.nextLine();

                        GerenciamentoFiscal gerenciamentoFiscal = new GerenciamentoFiscal(vendas);

                        switch (opcaoRelatorio) {
                            case 1:
                                // Relatório de vendas diárias
                                System.out.print("Digite a data no formato 'dd/MM/yyyy': ");
                                String dataStr = scanner.nextLine();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                                try {
                                    Date data = sdf.parse(dataStr);
                                    double vendasDiarias = gerenciamentoFiscal.calcularVendasDiarias(data);
                                    System.out.println("Vendas diárias em " + dataStr + ": R$" + vendasDiarias);
                                } catch (ParseException e) {
                                    System.out.println("Formato de data inválido. Use 'dd/MM/yyyy'.");
                                }
                                break;

                            case 2:
                                // Relatório de vendas mensais
                                System.out.print("Digite o ano e mês no formato 'yyyy/MM': ");
                                String anoMesStr = scanner.nextLine();
                                SimpleDateFormat sdfAnoMes = new SimpleDateFormat("yyyy/MM");

                                try {
                                    Date anoMes = sdfAnoMes.parse(anoMesStr);
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(anoMes);
                                    int ano = cal.get(Calendar.YEAR);
                                    int mes = cal.get(Calendar.MONTH) + 1; // Janeiro é 0, então somamos 1
                                    double vendasMensais = gerenciamentoFiscal.calcularVendasMensais(ano, mes);
                                    System.out.println("Vendas mensais em " + anoMesStr + ": R$" + vendasMensais);
                                } catch (ParseException e) {
                                    System.out.println("Formato de ano/mês inválido. Use 'yyyy/MM'.");
                                }
                                break;

                            case 3:
                                // Relatório de vendas anuais
                                System.out.print("Digite o ano: ");
                                int ano = scanner.nextInt();
                                scanner.nextLine();
                                double vendasAnuais = gerenciamentoFiscal.calcularVendasAnuais(ano);
                                System.out.println("Vendas anuais em " + ano + ": R$" + vendasAnuais);
                                break;

                            default:
                                System.out.println("Opção de relatório inválida. Tente novamente.");
                                break;
                        }
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
                            System.out.println("Escolha a categoria do produto:");
                            System.out.println("1. Hortifrúti");
                            System.out.println("2. Alimentos");
                            System.out.println("3. Congelados");
                            System.out.println("4. Bebidas");
                            System.out.println("5. Sair do modo de adição");
                            System.out.print("Selecione a categoria ou '5' para sair: ");
                            int categoriaChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (categoriaChoice == 5) {
                                continuarAdicionandoProdutos = false;
                                continue;
                            }

                            String categoria = "";

                            switch (categoriaChoice) {
                                case 1:
                                    categoria = "Hortifrúti";
                                    break;
                                case 2:
                                    categoria = "Alimentos";
                                    break;
                                case 3:
                                    categoria = "Congelados";
                                    break;
                                case 4:
                                    categoria = "Bebidas";
                                    break;
                                default:
                                    System.out.println("Categoria inválida.");
                                    continue; // Volte ao menu de categoria
                            }

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

                            Produto novoProduto = new Produto(nomeProduto, precoProduto, categoria);
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
                        System.out.println("Lista de Funcionários:");
                        for (Funcionario funcionarioS : funcionarios) {
                            System.out.println("ID: " + funcionarioS.getId() + ", Nome: " + funcionarioS.getNome());
                        }

                        System.out.print("Digite o ID do funcionário a ser editado: ");
                        int idEditar = Integer.parseInt(scanner.nextLine());
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

                    case 12:
                        System.out.println("Relatórios do Caixa " + caixaAtual.getNumeroCaixa());
                        System.out.println("Responsável: " + caixaAtual.getFuncionarioResponsavel().getNome());
                        System.out.println("Cargo: " + (caixaAtual.getFuncionarioResponsavel() instanceof Administrador ? "Administrador" : "Funcionário"));
                        System.out.println("1. Relatório de Vendas Diárias");
                        System.out.println("2. Relatório de Vendas Mensais");
                        System.out.println("3. Relatório de Vendas Anuais");
                        System.out.print("Escolha o tipo de relatório: ");
                        int relatorioChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (relatorioChoice) {
                            case 1:
                                // Relatório de Vendas Diárias
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                Date dataAtual = new Date();
                                double vendasDiarias = caixaAtual.calcularRendimentoDiario(dataAtual); // Corrigir o cálculo
                                System.out.println("Relatório de Vendas Diárias em " + sdf.format(dataAtual) + " para o Caixa " + caixaAtual.getNumeroCaixa() + ": R$" + vendasDiarias);
                                break;

                            case 2:
                                // Relatório de Vendas Mensais
                                System.out.print("Digite o ano e mês no formato 'yyyy/MM': ");
                                String anoMesStr = scanner.nextLine();
                                SimpleDateFormat sdfAnoMes = new SimpleDateFormat("yyyy/MM");

                                try {
                                    Date anoMes = sdfAnoMes.parse(anoMesStr);
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(anoMes);
                                    int ano = cal.get(Calendar.YEAR);
                                    int mes = cal.get(Calendar.MONTH) + 1;
                                    double vendasMensais = caixaAtual.calcularRendimentoMensal(ano, mes); // Corrigir o cálculo
                                    System.out.println("Relatório de Vendas Mensais em " + anoMesStr + " para o Caixa " + caixaAtual.getNumeroCaixa() + ": R$" + vendasMensais);
                                } catch (ParseException e) {
                                    System.out.println("Formato de ano/mês inválido. Use 'yyyy/MM'.");
                                }
                                break;

                            case 3:
                                // Relatório de Vendas Anuais
                                System.out.print("Digite o ano: ");
                                int ano = scanner.nextInt();
                                scanner.nextLine();
                                double vendasAnuais = caixaAtual.calcularRendimentoAnual(ano); // Corrigir o cálculo
                                System.out.println("Relatório de Vendas Anuais em " + ano + " para o Caixa " + caixaAtual.getNumeroCaixa() + ": R$" + vendasAnuais);
                                break;

                            default:
                                System.out.println("Opção de relatório inválida. Tente novamente.");
                                break;
                        }
                        break;

                }
            }
        }

    }

    private static Funcionario criarFuncionario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do funcionário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a senha do funcionário: ");
        String senha = scanner.nextLine();
        System.out.print("O funcionário é um administrador? (true/false): ");
        boolean isAdm = scanner.nextBoolean();

        int id = ultimoId; // Gere o ID automaticamente
        ultimoId++; // Incremente o último ID usado

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

        int opcao;
        do {
            System.out.println("Escolha o que deseja editar:");
            System.out.println("1. Nome");
            System.out.println("2. Senha");
            System.out.println("0. Sair");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Digite o novo nome do funcionário: ");
                    String novoNome = scanner.nextLine();
                    funcionario.setNome(novoNome);
                    System.out.println("Nome do funcionário editado com sucesso!");
                    break;
                case 2:
                    System.out.print("Digite a nova senha do funcionário: ");
                    String novaSenha = scanner.nextLine();
                    funcionario.setSenha(novaSenha);
                    System.out.println("Senha do funcionário editada com sucesso!");
                    break;
                case 0:
                    System.out.println("Saindo do menu de edição.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (opcao != 0);
    }

    private static Funcionario encontrarFuncionarioPorID(List<Funcionario> funcionarios, int id) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
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

    private static Caixa encontrarCaixaPorNumero(int numeroCaixa) {
        for (Caixa caixa : caixas) {
            if (caixa.getNumeroCaixa() == numeroCaixa) {
                return caixa;
            }
        }
        return null;
    }

    private static Login encontrarLoginPorNomeUsuario(String nomeUsuario) {
        for (Login login : logins) {
            if (login.getNome().equals(nomeUsuario)) {
                return login;
            }
        }
        return null;
    }
}
