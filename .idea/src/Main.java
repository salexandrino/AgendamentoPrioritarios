import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AgendaAtendimentoHeap agenda = new AgendaAtendimentoHeap();

        // 1. Cadastra automaticamente os 3 pacientes iniciais
        inicializarPacientesPadrao(agenda);

        boolean executando = true;

        // 2. Loop principal: Mostra o MENU LOGO DE INÍCIO
        while (executando) {
            System.out.println("\n====================================");
            System.out.println("   AGENDA DE ATENDIMENTOS DA UBS    ");
            System.out.println("====================================");
            System.out.println("1 - Cadastrar novo paciente");
            System.out.println("2 - Ver próximo paciente prioritário (sem remover)");
            System.out.println("3 - Chamar/Atender paciente prioritário (remover)");
            System.out.println("4 - Sair do sistema");
            System.out.print("Escolha uma opção: ");

            try {
                int opcaoMenu = Integer.parseInt(scanner.nextLine());

                switch (opcaoMenu) {
                    case 1:
                        cadastrarPaciente(scanner, agenda);
                        break;

                    case 2:
                        System.out.println("\n=== PRÓXIMO PACIENTE PRIORITÁRIO ===");
                        Paciente proximo = agenda.quemEhOProximo();
                        if (proximo != null) {
                            System.out.println(proximo);
                        } else {
                            System.out.println("Não há pacientes na agenda.");
                        }
                        break;

                    case 3:
                        System.out.println("\n=== CHAMAR PACIENTE PARA ATENDIMENTO ===");
                        Paciente removido = agenda.removerPacientePrioritario();
                        if (removido != null) {
                            System.out.println("Paciente chamado e removido da agenda:");
                            System.out.println(removido);
                        } else {
                            System.out.println("Não há pacientes na agenda.");
                        }
                        break;

                    case 4:
                        executando = false;
                        System.out.println("\nEncerrando o sistema da UBS. Até logo!");
                        break;

                    default:
                        System.out.println("\nOpção inválida! Escolha um número de 1 a 4.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nEntrada inválida! Digite apenas o número da opção.");
            }
        }

        scanner.close();
    }

    private static void inicializarPacientesPadrao(AgendaAtendimentoHeap agenda) {
        Paciente p1 = new Paciente(
                12345678901L,
                "Neuza Carvalho",
                "89800001111",
                TipoAtendimento.CONSULTA_AGENDADA,
                62
        );

        Paciente p2 = new Paciente(
                98765432100L,
                "Saymon Ryan",
                "89800002222",
                TipoAtendimento.VACINACAO,
                10
        );

        Paciente p3 = new Paciente(
                45678912300L,
                "Roberto Alves",
                "89800003333",
                TipoAtendimento.TRIAGEM,
                35
        );

        agenda.cadastrarAtendimentoDia(p1);
        agenda.cadastrarAtendimentoDia(p2);
        agenda.cadastrarAtendimentoDia(p3);

        System.out.println(">> Sistema iniciado com 3 pacientes pré-cadastrados (Neuza - 62a, Saymon - 10a, Roberto - 35a).");
    }

    private static void cadastrarPaciente(Scanner scanner, AgendaAtendimentoHeap agenda) {
        System.out.println("\n--- CADASTRO DE PACIENTE ---");

        try {
            System.out.print("CPF: ");
            long cpf = Long.parseLong(scanner.nextLine());

            System.out.print("Nome completo: ");
            String nome = scanner.nextLine();

            System.out.print("Cartão SUS: ");
            String cartaoSUS = scanner.nextLine();

            System.out.print("Idade: ");
            int idade = Integer.parseInt(scanner.nextLine());

            TipoAtendimento tipoAtendimento = null;
            while (tipoAtendimento == null) {
                System.out.println("Tipo de atendimento:");
                System.out.println("1 - Triagem");
                System.out.println("2 - Vacinação");
                System.out.println("3 - Consulta agendada");
                System.out.print("Escolha uma opção: ");

                int opcao = Integer.parseInt(scanner.nextLine());
                if (opcao == 1) tipoAtendimento = TipoAtendimento.TRIAGEM;
                else if (opcao == 2) tipoAtendimento = TipoAtendimento.VACINACAO;
                else if (opcao == 3) tipoAtendimento = TipoAtendimento.CONSULTA_AGENDADA;
                else System.out.println("Opção inválida!");
            }

            Paciente paciente = new Paciente(cpf, nome, cartaoSUS, tipoAtendimento, idade);
            agenda.cadastrarAtendimentoDia(paciente);
            System.out.println("✔ Paciente cadastrado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: Digite apenas números no CPF e Idade.");
        }
    }
}