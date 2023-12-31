package com.mycompany.mercadinho;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.util.Iterator;

/**
 * Classe que representa o sistema de um mercadinho.
 */
public class Mercadinho {

    // Lista estática de 5 caixa
    private static Caixa[] caixas = new Caixa[5]; //Q5

    // Lista estática de logins
    private static List<Login> logins = new ArrayList<>();

    // Usuário logado no sistema
    private static Login usuarioLogado = null;

    // Lista de funcionários cadastrados no sistema
    private List<Funcionario> funcionariosCadastrados = new ArrayList<>();

    // Lista de administradores cadastrados no sistema
    private List<Administrador> administradoresCadastrados = new ArrayList<>();

    // Lista de logins cadastrados
    private List<Login> loginsCadastrados;

    // Último ID utilizado, inicializado com um valor desejado
    private static int ultimoId = 1;

    // Objeto para gerenciamento do estoque
    private Estoque estoque = new Estoque();

    // Administrador do mercadinho
    private Administrador administrador = new Administrador();

    // Lista de vendas realizadas
    private List<Venda> vendas = new ArrayList<>();

    // Lista de clientes cadastrados
    private List<Cliente> clientes = new ArrayList<>();

    // Próximo ID de venda a ser utilizado
    private int nextVendaId = 1;

    // Caixa atual do sistema
    private Caixa caixaAtual = new Caixa();

    /**
     * Metodo menu de administrador, ele define os cases que o ADM tem acesso
     */
    //instancia unica da classe 
    private static Mercadinho instance;

    /**
     * Construtor de mercadinho que sera usado para o padrão de preojeto
     * Sigleton
     *
     */
    private Mercadinho() {

    }

    /**
     * Método público estático para obter a instância única da classe mercadinho
     */
    public static Mercadinho getInstance() {
        if (instance == null) {
            instance = new Mercadinho();
        }
        return instance;
    }

    /**
     *
     * Inicialização Lazy com Sincronização (Método Tradicional): Neesta
     * abordagem, a instância Singleton é inicializada apenas quando necessário
     * (inicialização preguiçosa) a sincronização é usada para garantir que
     * apenas uma thread por vez possa criar a instância.
     *
     *
     * public class Mercadinho { private static Mercadinho instance;
     *
     * private Mercadinho() { // Construtor privado para evitar instanciamento
     * externo }
     *
     * public static synchronized Mercadinho getInstance() { if (instance ==
     * null) { instance = new Mercadinho(); } return instance; } }
     *
     */
    private void menuAdmin(Administrador adm) {
        while (true) {
            System.out.println("______________________ Menu ADM ________________________");
            System.out.println("Sistema de Mercadinho");
            System.out.println("1. Realizar Venda"); //Q8 Q10
            System.out.println("2. Cancelar Venda");
            System.out.println("3. Verificar Estoque");
            System.out.println("4. Gerar Relatório Fiscal");
            System.out.println("5. Cadastrar Cliente"); //Q7
            System.out.println("6. Verificar Cliente");
            System.out.println("7. Adicionar Produto");
            System.out.println("8. Editar Produto");
            System.out.println("9. Sair do sistema");
            System.out.println("10. Criar Credenciais de Funcionário"); //Q6
            System.out.println("11.Editar Funcionário");
            System.out.println("12.Relatórios do Caixa");
            System.out.println("13.Editar Cliente");
            System.out.println("______________________________________________");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Produtos disponíveis no estoque:");
                   
                    List<Produto>produtos = estoque.exibirEstoque();

                    List<VendaItem> itensDaVenda = new ArrayList<>();
                    boolean continuarComprando = true;
                    double valorTotalVenda = 0.0; // Variável para rastrear o valor total da venda

                    while (continuarComprando) {
                        System.out.print("Digite o ID do produto a ser vendido (ou 'S' para finalizar a compra): ");
                        String input = scanner.nextLine().trim();  // Remover espaços extras

                        if ("S".equalsIgnoreCase(input)) {
                            continuarComprando = false;
                            break;
                        }

                        try {
                            int produtoId = Integer.parseInt(input);
                            Produto produtoAVender = encontrarProdutoPorID( produtos, produtoId);

                            if (produtoAVender != null && produtoAVender.getQuantidadeInicial() > 0) {
                                System.out.print("Digite a quantidade a ser vendida: ");
                                int quantidadeAVender = scanner.nextInt();
                                scanner.nextLine();

                                if (quantidadeAVender > 0 && quantidadeAVender <= produtoAVender.getQuantidadeInicial()) {
                                    VendaItem item = new VendaItem(produtoAVender, quantidadeAVender);
                                    itensDaVenda.add(item);

                                    double valorItem = quantidadeAVender * produtoAVender.getValorDoProduto();
                                    valorTotalVenda += valorItem; // Adiciona o valor do item ao valor total da venda
                                } else {
                                    System.out.println("Quantidade inválida. Verifique o estoque disponível.");
                                }
                            } else {
                                System.out.println("Produto não encontrado ou estoque esgotado.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("ID inválido. Digite um número válido ou 'S' para finalizar a compra.");
                        }
                    }

                    if (!itensDaVenda.isEmpty()) {
                        int idVenda = nextVendaId;
                        Venda venda = new Venda();

                        for (VendaItem item : itensDaVenda) {
                            venda.adicionarItem(item);
                        }

                        venda.realizarVenda(estoque);


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

                    Manipularjson manipularjson = new Manipularjson();

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

                        // Chamada para salvar a lista atualizada
                        Manipularjson.EscreverCliente(clientes);
                    }
                    break;

                case 6:
                    System.out.print("Digite o CPF do cliente a ser verificado: ");
                    String cpfVerificar = scanner.nextLine();
                    Manipularjson.LerCliente();
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
                        System.out.print("Selecione a categoria (1 a 5) ou '5' para sair: ");

                        String categoriaChoice = scanner.nextLine();

                        if (categoriaChoice.equalsIgnoreCase("5")) {
                            continuarAdicionandoProdutos = false;
                            continue;
                        }

                        String categoria = "";

                        switch (categoriaChoice) {
                            case "1":
                                categoria = "Hortifrúti";
                                break;
                            case "2":
                                categoria = "Alimentos";
                                break;
                            case "3":
                                categoria = "Congelados";
                                break;
                            case "4":
                                categoria = "Bebidas";
                                break;
                            default:
                                System.out.println("Categoria inválida.");
                                continue; // Volte ao menu de categoria
                        }

                        System.out.print("Digite o nome do novo produto (ou pressione S para parar): ");
                        String nomeProduto = scanner.nextLine();
                        if (nomeProduto.equalsIgnoreCase("S")) {
                            continuarAdicionandoProdutos = false;
                            continue;
                        }

                        System.out.print("Digite o preço do produto: ");
                        double precoProduto = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Digite a quantidade inicial em estoque: ");
                        int quantidadeInicial = scanner.nextInt();
                        scanner.nextLine();

                        Produto produto = new Produto(nomeProduto, precoProduto, categoria, quantidadeInicial);

                        Manipularjson.EscreverEstoque(produto);
                        System.out.println("Produto adicionado ao estoque com sucesso!");
                    }
                    break;
                    
                case 8:
                	System.out.println("Editar Produto");
                	
                	produtos = estoque.exibirEstoque();
                	
                	System.out.print("Digite o ID do produto a ser editado: ");
                    int idProduto = Integer.parseInt(scanner.nextLine());
                    
                    System.out.println("Escolha a nova categoria do produto:");
                    System.out.println("1. Hortifrúti");
                    System.out.println("2. Alimentos");
                    System.out.println("3. Congelados");
                    System.out.println("4. Bebidas");
                    System.out.println("5. Sair do modo de edição");

                    String categoriaChoice = scanner.nextLine();

                    String categoria = "";

                    switch (categoriaChoice) {
                        case "1":
                            categoria = "Hortifrúti";
                            break;
                        case "2":
                            categoria = "Alimentos";
                            break;
                        case "3":
                            categoria = "Congelados";
                            break;
                        case "4":
                            categoria = "Bebidas";
                            break;
                        default:
                            System.out.println("Categoria inválida.");
                            continue; // Volte ao menu de categoria
                    }
                    
                    System.out.print("Digite o novo nome do produto: ");
                    String nomeProduto = scanner.nextLine();                    

                    System.out.print("Digite o novo preço do produto: ");
                    double precoProduto = scanner.nextDouble();
                    scanner.nextLine();
                    
                    System.out.print("Digite a nova quantidade em estoque: ");
                    int quantidadeInicial = scanner.nextInt();
                    scanner.nextLine();
                    
                    Produto produto = new Produto(nomeProduto, precoProduto, categoria, quantidadeInicial);
                    produto.setId(idProduto);
                    
                    Manipularjson.EscreverEstoqueEdicao(produto);
                    
                    System.out.println("Produto atualizado no estoque com sucesso!");
                    
                    break;

                case 9:
                    System.out.println("Saindo do sistema.");
                    scanner.close();
                    System.exit(0);
                    break;

                case 10:

                    Funcionario funcionario = criarFuncionario();
                    List<Funcionario> novaListaFuncionarios = new ArrayList<>(funcionariosCadastrados);
                    novaListaFuncionarios.add(funcionario);
                    funcionariosCadastrados = novaListaFuncionarios;

                    if (funcionario instanceof Administrador) {
                        List<Administrador> novaListaAdministradores = new ArrayList<>(administradoresCadastrados);
                        novaListaAdministradores.add((Administrador) funcionario);
                        administradoresCadastrados = novaListaAdministradores;
                    }

                    System.out.println("Funcionário criado com sucesso!");

                    Manipularjson escreverFuncionario = new Manipularjson();

                    escreverFuncionario.Escreverfuncionario(funcionariosCadastrados);

                    Manipularjson escreverAdministrador = new Manipularjson();

                    escreverAdministrador.EscreverAdministrador(administradoresCadastrados);

                case 11:
                    System.out.println("Lista de Funcionários:");
                    for (Funcionario funcionarioS : funcionariosCadastrados) {
                        System.out.println("ID: " + funcionarioS.getId() + ", Nome: " + funcionarioS.getNome());
                    }

                    System.out.print("Digite o ID do funcionário a ser editado: ");
                    int idEditar = Integer.parseInt(scanner.nextLine());
                    Funcionario funcionarioParaEditar = encontrarFuncionarioPorID(funcionariosCadastrados, idEditar);
                    if (funcionarioParaEditar != null) {
                        editarCredenciais(funcionarioParaEditar);
                        System.out.println("Credenciais do funcionário editadas com sucesso!");
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
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

                case 13:
                    System.out.println("Editar Cliente");
                    System.out.print("Digite o CPF do cliente a ser editado: ");
                    String cpfEditar = scanner.nextLine();

                    // Inicializar a lista de clientes chamando a função LerCliente
                    List<Cliente> cliente = Manipularjson.LerCliente();

                    // Verificar se a lista de clientes foi carregada corretamente
                    if (cliente != null) {
                        // Chamar a função de edição do cliente
                        editarClienteMenu(cliente, cpfEditar);

                        // Salvar a lista atualizada no arquivo JSON
                        Manipularjson.EscreverCliente(cliente);
                    } else {
                        System.out.println("Erro ao carregar a lista de clientes.");
                    }
                    break;
            }
        }
    }

    /**
     * Metodo menu de Funcionario, ele define os cases que o funcionario tem
     * acesso
     */
    private void menuFuncionario(Funcionario funcionario) {
        while (true) {
            System.out.println("______________________ Menu Funcionário ________________________");
            System.out.println("Sistema de Mercadinho");
            System.out.println("1. Realizar Venda");
            System.out.println("2. Cancelar Venda");
            System.out.println("3. Cadastrar Cliente");
            System.out.println("4. Verificar Cliente");
            System.out.println("5. Sair do sistema");
            System.out.println("6. Editar CLiente");
            System.out.println("______________________________________________");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Produtos disponíveis no estoque:");
                List<Produto>produtos = estoque.exibirEstoque();

                    List<VendaItem> itensDaVenda = new ArrayList<>();
                    boolean continuarComprando = true;
                    double valorTotalVenda = 0.0;

                    while (continuarComprando) {
                        System.out.print("Digite o ID do produto a ser vendido (ou '0' para finalizar a compra): ");
                        String input = scanner.nextLine();

                        if ("0".equals(input)) {
                            continuarComprando = false;
                            break;
                        }

                        int produtoId = Integer.parseInt(input);
                        Produto produtoAVender = encontrarProdutoPorID(produtos, produtoId);

                        if (produtoAVender != null) {
                            System.out.print("Digite a quantidade a ser vendida: ");
                            int quantidadeAVender = scanner.nextInt();
                            scanner.nextLine();

                            VendaItem item = new VendaItem(produtoAVender, quantidadeAVender);
                            itensDaVenda.add(item);

                            double valorItem = quantidadeAVender * produtoAVender.getValorDoProduto();
                            valorTotalVenda += valorItem;
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
                        Manipularjson.EscreverCliente(clientes);
                    }
                    break;

                case 4:
                    System.out.print("Digite o CPF do cliente a ser verificado: ");
                    String cpfVerificar = scanner.nextLine();

                    List<Cliente> clientes = Manipularjson.LerCliente();
                    boolean clienteExiste = verificarCliente(clientes, cpfVerificar);
                    if (clienteExiste) {
                        System.out.println("Cliente encontrado no sistema.");
                    } else {
                        System.out.println("Cliente não encontrado no sistema.");
                    }
                    break;

                case 5:
                    System.out.println("Saindo do sistema.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;

                case 6:
                    System.out.println("Editar Cliente");
                    System.out.print("Digite o CPF do cliente a ser editado: ");
                    String cpfEditar = scanner.nextLine();

                    // Inicializar a lista de clientes chamando a função LerCliente
                    List<Cliente> cliente = Manipularjson.LerCliente();

                    // Verificar se a lista de clientes foi carregada corretamente
                    if (cliente != null) {
                        // Chamar a função de edição do cliente
                        editarClienteMenu(cliente, cpfEditar);

                        // Salvar a lista atualizada no arquivo JSON
                        Manipularjson.EscreverCliente(cliente);
                    } else {
                        System.out.println("Erro ao carregar a lista de clientes.");
                    }
                    break;

            }
        }
    }

    /**
     * Cria e retorna um novo funcionário com base nas informações inseridas
     * pelo usuário.
     *
     * @return O funcionário criado.
     */
    private static Funcionario criarFuncionario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do funcionário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a senha do funcionário: ");
        String senha = scanner.nextLine();
        System.out.print("O funcionário é um administrador? (true/false): ");
        boolean isAdm = scanner.nextBoolean();

        int id = ultimoId; // Use o valor atual de ultimoId como ID
        ultimoId++; // Incremente o último ID usado para o próximo funcionário

        if (isAdm) {
            return new Administrador(nome, id, senha);
        } else {
            return new Funcionario(nome, id, senha);
        }
    }

    /**
     * Permite editar as credenciais (nome e senha) de um funcionário.
     *
     * @param funcionario O funcionário cujas credenciais serão editadas.
     */
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

    /**
     * Encontra um funcionário com base no ID especificado.
     *
     * @param funcionarios A lista de funcionários onde a pesquisa será
     * realizada.
     * @param id O ID do funcionário a ser encontrado.
     * @return O funcionário correspondente se encontrado; null, caso contrário.
     */
    private static Funcionario encontrarFuncionarioPorID(List<Funcionario> funcionarios, int id) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                return funcionario;
            }
        }
        return null;
    }

    /**
     * Encontra um produto no estoque com base no ID especificado.
     *
     * @param estoque O estoque onde a pesquisa será realizada.
     * @param produtoId O ID do produto a ser encontrado.
     * @return O produto correspondente se encontrado; null, caso contrário.
     */
    private static Produto encontrarProdutoPorID(List<Produto>produtos, int produtoId) {
        for (Produto produto : produtos) {
            if (produto.getId() == produtoId) {
                return produto;
            }
        }
        return null;
    }

    /**
     * Encontra uma venda na lista de vendas com base no ID de venda
     * especificado.
     *
     * @param vendas A lista de vendas onde a pesquisa será realizada.
     * @param idVenda O ID da venda a ser encontrado.
     * @return A venda correspondente se encontrada; null, caso contrário.
     */
    private static Venda encontrarVendaPorID(List<Venda> vendas, int idVenda) {
        for (Venda venda : vendas) {
            if (venda.getId() == idVenda) {
                return venda;
            }
        }
        return null;
    }

    /**
     * Verifica se um cliente com o CPF especificado está na lista de clientes.
     *
     * @param clientes A lista de clientes onde a verificação será realizada.
     * @param cpfVerificar O CPF do cliente a ser verificado.
     * @return true se o cliente for encontrado na lista; false, caso contrário.
     */
    private static boolean verificarCliente(List<Cliente> clientes, String cpfVerificar) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpfVerificar)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Permite editar informações de um cliente com base no CPF.
     *
     * @param clientes A lista de clientes onde a edição será realizada.
     * @param cpfEditar O CPF do cliente a ser editado.
     */
    private static void editarClienteMenu(List<Cliente> clientes, String cpfEditar) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpfEditar)) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Cliente encontrado. O que você deseja editar?");
                System.out.println("1. Nome");
                System.out.println("2. Telefone");
                System.out.println("3. Endereço");
                System.out.println("4. Email");
                System.out.println("5. Sair da edição");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.print("Novo nome: ");
                        String novoNome = scanner.nextLine();
                        cliente.setNome(novoNome);
                        System.out.println("Nome atualizado com sucesso.");
                        break;
                    case 2:
                        System.out.print("Novo telefone: ");
                        String novoTelefone = scanner.nextLine();
                        cliente.setTelefone(novoTelefone);
                        System.out.println("Telefone atualizado com sucesso.");
                        break;
                    case 3:
                        System.out.print("Novo endereço: ");
                        String novoEndereco = scanner.nextLine();
                        cliente.setEndereco(novoEndereco);
                        System.out.println("Endereço atualizado com sucesso.");
                        break;
                    case 4:
                        System.out.print("Novo email: ");
                        String novoEmail = scanner.nextLine();
                        cliente.setEmail(novoEmail);
                        System.out.println("Email atualizado com sucesso.");
                        break;
                    case 5:
                        System.out.println("Saindo da edição.");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
                return; // Cliente encontrado e atualizado, podemos sair do loop
            }
        }
        System.out.println("Cliente não encontrado.");
    }

    /**
     * Encontra um caixa com base no número do caixa especificado.
     *
     * @param numeroCaixa O número do caixa a ser encontrado.
     * @return O caixa correspondente se encontrado; null, caso contrário.
     */
    /**
     * Realiza o processo de login no sistema, permitindo que administradores e
     * funcionários acessem as funcionalidades do sistema.
     */
    private List<Funcionario> carregarFuncionariosDoJson() {
        List<Funcionario> funcionariosCadastrados = Manipularjson.LerFuncionario();
        if (funcionariosCadastrados == null) {
            System.out.println("Erro ao ler os dados dos funcionários.");
        }
        return funcionariosCadastrados;
    }

    private List<Administrador> carregarAdministradorDoJson() {
        List<Administrador> AdministradoresCadastrados = Manipularjson.LerAdministrador();
        if (AdministradoresCadastrados == null) {
            System.out.println("Erro ao ler os dados dos Administrador.");
        }
        return AdministradoresCadastrados;
    }

    public void login() {
        funcionariosCadastrados = carregarFuncionariosDoJson();
        administradoresCadastrados = carregarAdministradorDoJson();

        Scanner scan = new Scanner(System.in);
        String nome;
        String senha;
        String opcao;

        System.out.println("\n---------- SEJA BEM VINDO AO MERCADINHO DA GRA ----------\n");
        System.out.println("Logar como\n"
                + "Adminstrador -> Digite 1\n"
                + "Funcionário  -> Digite 2");
        System.out.printf("Opção: ");
        opcao = scan.nextLine();

        switch (opcao) {
            case "1": {
                System.out.println("\n---------- Login como Administrador ----------\n");
                System.out.printf("Nome: ");
                nome = scan.nextLine();

                // Encontre o administrador com o nome especificado
                Administrador admin = encontrarAdministradorPorNome(nome);

                if (admin != null) {
                    System.out.println("Olá " + admin.getNome());
                    System.out.printf("Digite sua senha: ");
                    senha = scan.nextLine();

                    if (admin.getSenha().equals(senha)) {
                        // Faça o que for necessário quando o login do administrador for bem-sucedido
                        SelecionarCaixa();
                        menuAdmin(admin);
                    } else {
                        System.out.println("Senha incorreta.");
                        login();
                    }
                } else {
                    System.out.println("Não existe um administrador com esse nome em nossa base de dados.");
                    System.out.println("Tente novamente...");
                    login();
                }
            }
            case "2": {
                System.out.println("\n---------- Login como Funcionário ----------\n");
                System.out.println("Nome: ");
                nome = scan.nextLine();

                // Encontre o funcionário com o nome especificado
                Funcionario func = encontrarFuncionarioPorNome(nome);

                if (func != null) {
                    System.out.println("Olá " + func.getNome());
                    System.out.printf("Digite sua senha: ");
                    senha = scan.nextLine();

                    if (func.getSenha().equals(senha)) {
                        // Faça o que for necessário quando o login do funcionário for bem-sucedido
                        SelecionarCaixa();
                        menuFuncionario(func);
                    } else {
                        System.out.println("Senha incorreta.");
                        login();
                    }
                } else {
                    System.out.println("Não existe um funcionário com esse nome em nossa base de dados.");
                    System.out.println("Tente novamente...");
                    login();
                }
            }
            default: {
                System.out.println("Não foi encontrada a opção");
                login();
            }
        }
    }

    /**
     * Encontra um administrador por nome na lista de administradores
     * cadastrados.
     *
     * @param nome O nome do administrador a ser encontrado.
     * @return O administrador correspondente se encontrado; null, caso
     * contrário.
     */
    private Administrador encontrarAdministradorPorNome(String nome) {
        for (Administrador admin : administradoresCadastrados) {
            if (admin.getNome().equals(nome)) {
                return admin;
            }
        }
        return null;
    }

    /**
     * Encontra um login com base no nome de usuário especificado na lista de
     * logins cadastrados.
     *
     * @param nomeUsuario O nome de usuário a ser encontrado.
     * @return O login correspondente se encontrado; null, caso contrário.
     * Entidades:
     *
     * /**
     * Encontra um funcionário por nome na lista de funcionários cadastrados.
     *
     * @param nome O nome do funcionário a ser encontrado.
     * @return O funcionário correspondente se encontrado; null, caso contrário.
     */
    private Funcionario encontrarFuncionarioPorNome(String nome) {
        for (Funcionario func : funcionariosCadastrados) {
            if (func.getNome().equals(nome)) {
                return func;
            }
        }
        return null;
    }

    /**
     * Permite ao usuário selecionar um caixa para operação.
     */
    private void SelecionarCaixa() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            caixas[i] = new Caixa(i + 1, vendas, vendas, new GerenciamentoFiscal(vendas));
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
    }

    private Caixa encontrarCaixaPorNumero(int numeroCaixa) {
        for (Caixa caixa : caixas) {
            if (caixa != null && caixa.getNumeroCaixa() == numeroCaixa) {
                return caixa;
            }
        }
        return null;
    }
    
    //Q17
    /**
     * Encontra um cliente na lista com base no número de telefone usando Iterator.
     *
     * @param telefone      O número de telefone a ser buscado.
     * @param listaClientes A lista de clientes.
     * @return O cliente encontrado, ou null se não encontrado.
     */
 public static Cliente findClienteByTelefone(String telefone, List<Cliente> listaClientes) {
        Iterator<Cliente> iterator = listaClientes.iterator();

        while (iterator.hasNext()) {
            Cliente cliente = iterator.next();
            if (cliente.getTelefone().equals(telefone)) {
                return cliente;
            }
        }
        return null;
    }
 
 //Metodo para imprimir cliente 
    public static void imprimirCliente(Cliente cliente) {
        System.out.println("Nome do Cliente: " + cliente.getNome());
        System.out.println("Telefone: " + cliente.getTelefone());
        
    }



   
}
