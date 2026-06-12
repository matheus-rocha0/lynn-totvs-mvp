package com.lynn.totvs;

import com.lynn.totvs.enums.ProdutoTotvs;
import com.lynn.totvs.enums.TipoInsight;
import com.lynn.totvs.model.*;
import com.lynn.totvs.service.GeradorInsight;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static GeradorInsight geradorInsight = new GeradorInsight();

    static List<Usuario> usuarios = new ArrayList<>();
    static List<Cliente> clientes = new ArrayList<>();
    static List<Transcricao> transcricoes = new ArrayList<>();

    static int contadorUsuario = 1;
    static int contadorCliente = 1;
    static int contadorTranscricao = 1;

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        carregarDadosExemplo();

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            opcao = lerInt();
            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> cadastrarCliente();
                case 3 -> enviarTranscricao();
                case 4 -> listarUsuarios();
                case 5 -> listarInsights();
                case 6 -> filtrarInsightsPorTipo();
                case 0 -> System.out.println("\nEncerrando LYNN. Até logo!");
                default -> System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }

    static void exibirMenu() {
        System.out.println("\n========================================");
        System.out.println("        LYNN - Plataforma TOTVS         ");
        System.out.println("========================================");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Cadastrar Cliente");
        System.out.println("3. Enviar Transcrição para Análise");
        System.out.println("4. Listar Usuários");
        System.out.println("5. Listar Insights Gerados");
        System.out.println("6. Filtrar Insights por Tipo");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    static void cadastrarUsuario() {
        System.out.println("\n--- Cadastrar Usuário ---");
        System.out.println("Tipo: 1-Vendedor | 2-Analista CS | 3-Operador Backoffice");
        System.out.print("Tipo: ");
        int tipo = lerInt();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Usuario usuario = switch (tipo) {
            case 1 -> {
                System.out.print("Território de atendimento: ");
                String territorio = scanner.nextLine();
                System.out.print("Meta mensal (R$): ");
                double meta = Double.parseDouble(scanner.nextLine());
                yield new Vendedor(contadorUsuario++, nome, email, territorio, meta);
            }
            case 2 -> {
                System.out.print("Tamanho da carteira (nº clientes): ");
                int carteira = lerInt();
                System.out.print("Taxa de retenção (%): ");
                double taxa = Double.parseDouble(scanner.nextLine());
                yield new AnalistaCS(contadorUsuario++, nome, email, carteira, taxa);
            }
            case 3 -> {
                System.out.print("Módulo ERP: ");
                String modulo = scanner.nextLine();
                System.out.print("Nível de acesso (1-5): ");
                int nivel = lerInt();
                yield new OperadorBackoffice(contadorUsuario++, nome, email, modulo, nivel);
            }
            default -> {
                System.out.println("Tipo inválido. Criando como Vendedor padrão.");
                yield new Vendedor(contadorUsuario++, nome, email, "Não informado", 0);
            }
        };

        usuarios.add(usuario);
        System.out.println("Usuário cadastrado: " + usuario.resumo());
    }

    static void cadastrarCliente() {
        System.out.println("\n--- Cadastrar Cliente ---");
        System.out.print("Razão Social: ");
        String razao = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        System.out.print("Segmento: ");
        String segmento = scanner.nextLine();
        System.out.println("Produto TOTVS atual: 1-PROTHEUS | 2-RM | 3-FLUIG | 4-RD_STATION | 5-EXACT_SPOTTER");
        System.out.print("Opção: ");
        int prodOpt = lerInt();
        ProdutoTotvs produto = ProdutoTotvs.values()[Math.max(0, Math.min(prodOpt - 1, ProdutoTotvs.values().length - 1))];

        Cliente cliente = new Cliente(contadorCliente++, razao, cnpj, segmento, produto);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado: " + cliente);
    }

    static void enviarTranscricao() {
        if (usuarios.isEmpty() || clientes.isEmpty()) {
            System.out.println("Cadastre ao menos um usuário e um cliente antes de enviar transcrições.");
            return;
        }

        System.out.println("\n--- Enviar Transcrição ---");
        System.out.println("Usuários disponíveis:");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println((i + 1) + ". " + usuarios.get(i).resumo());
        }
        System.out.print("Escolha o usuário: ");
        int uIdx = lerInt() - 1;

        System.out.println("Clientes disponíveis:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i));
        }
        System.out.print("Escolha o cliente: ");
        int cIdx = lerInt() - 1;

        System.out.print("Cole o texto da transcrição: ");
        String texto = scanner.nextLine();

        Usuario usuario = usuarios.get(uIdx);
        Cliente cliente = clientes.get(cIdx);
        Metadado metadado = new Metadado(contadorTranscricao, usuario, cliente, cliente.getProdutoAtual(), "LYNN-MVP");
        Transcricao transcricao = new Transcricao(contadorTranscricao++, texto, metadado);
        transcricoes.add(transcricao);

        System.out.println("\nProcessando análise semântica...");
        var insight = geradorInsight.processar(transcricao);
        System.out.println("\n✔ Análise concluída!");
        insight.exibir();
    }

    static void listarUsuarios() {
        System.out.println("\n--- Usuários Cadastrados ---");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        usuarios.forEach(u -> System.out.println(u.resumo()));
    }

    static void listarInsights() {
        System.out.println("\n--- Insights Gerados ---");
        geradorInsight.listarTodos();
    }

    static void filtrarInsightsPorTipo() {
        System.out.println("\nFiltrar por tipo: 1-UPSELL | 2-CROSS_SELL | 3-CHURN | 4-BUG_SISTEMA | 5-FRICCAO_UX");
        System.out.print("Opção: ");
        int opt = lerInt();
        TipoInsight tipo = TipoInsight.values()[Math.max(0, Math.min(opt - 1, TipoInsight.values().length - 1))];
        var lista = geradorInsight.filtrarPorTipo(tipo);
        if (lista.isEmpty()) {
            System.out.println("Nenhum insight do tipo " + tipo + " encontrado.");
        } else {
            lista.forEach(i -> { i.exibir(); System.out.println(); });
        }
    }

    static void carregarDadosExemplo() {
        Vendedor v1 = new Vendedor(contadorUsuario++, "Carlos Andrade", "carlos@totvs.com", "Sudeste", 150000.0);
        AnalistaCS cs1 = new AnalistaCS(contadorUsuario++, "Fernanda Lima", "fernanda@totvs.com", 45, 92.5);
        OperadorBackoffice op1 = new OperadorBackoffice(contadorUsuario++, "Ricardo Souza", "ricardo@totvs.com", "Fiscal", 3);
        usuarios.add(v1);
        usuarios.add(cs1);
        usuarios.add(op1);

        Cliente c1 = new Cliente(contadorCliente++, "Indústria Alfa Ltda", "12.345.678/0001-90", "Manufatura", ProdutoTotvs.RM);
        Cliente c2 = new Cliente(contadorCliente++, "Comercial Beta S.A.", "98.765.432/0001-10", "Varejo", ProdutoTotvs.PROTHEUS);
        clientes.add(c1);
        clientes.add(c2);

        Metadado m1 = new Metadado(1, v1, c1, ProdutoTotvs.RM, "RD Station CRM");
        Transcricao t1 = new Transcricao(contadorTranscricao++,
                "Na reunião de hoje o cliente relatou que está perdendo muito tempo cruzando impostos na mão. " +
                "Mencionou que a concorrente oferece um módulo automatizado. Precisaria de algo similar urgente.",
                m1);
        transcricoes.add(t1);
        geradorInsight.processar(t1);
    }

    static int lerInt() {
        try {
            int val = Integer.parseInt(scanner.nextLine().trim());
            return val;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
