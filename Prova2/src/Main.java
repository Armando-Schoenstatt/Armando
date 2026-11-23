import java.util.List;
import java.util.Scanner;


public class Main {
    private static final Scanner ENTRADA = new Scanner(System.in, "UTF-8");
    private static final SistemaLocadora SISTEMA = new SistemaLocadora();

    public static void main(String[] args) {
        criarDadosIniciais(); 
        try {
            executarMenu();
        } catch (EntradaEncerrada e) {
            System.out.println("\nEntrada encerrada. Finalizando programa.");
        } finally {
            ENTRADA.close();
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        System.out.flush();
        if (!ENTRADA.hasNextLine()) throw new EntradaEncerrada();
        return ENTRADA.nextLine().trim();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            String texto = lerTexto(mensagem);
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite um numero inteiro.");
            }
        }
    }

    private static double lerDecimal(String mensagem) {
        while (true) {
            String texto = lerTexto(mensagem).replace(',', '.');
            try {
                double valor = Double.parseDouble(texto);
                if (Double.isFinite(valor)) return valor;
            } catch (NumberFormatException e) {
                // Mensagem comum abaixo.
            }
            System.out.println("Entrada invalida. Digite um numero (ex.: 150,50).");
        }
    }

    private static void criarDadosIniciais() {
        SISTEMA.cadastrarVeiculo(new Carro("ABC1234", "Onix", 2022, 150, 20000, 4));
        SISTEMA.cadastrarVeiculo(new Moto("DEF5678", "CG 160", 2023, 80, 10000, 160));
        SISTEMA.cadastrarVeiculo(new Van("GHI9012", "Sprinter", 2021, 300, 50000, 15));
        SISTEMA.cadastrarAluguel(new AluguelBasico(1, 3));
        SISTEMA.cadastrarAluguel(new AluguelExecutivo(2, 5));
    }

    private static void executarMenu() {
        while (true) {
            mostrarMenu();
            int opcao = lerInteiro("Escolha uma opcao: ");
            if (opcao == 0) {
                System.out.println("Sistema encerrado.");
                return;
            }
            try {
                switch (opcao) {
                    case 1: associarVeiculo(); break;
                    case 2: exibirVeiculos(); break;       
                    case 3: exibirAlugueis(); break;       
                    case 4: exibirDisponiveis(); break;   
                    case 5: avaliarObjeto(); break;
                    case 6: realizarInspecao(); break;     
                    case 7: removerManutencao(); break;
                    case 8: colocarEmManutencao(); break;  
                    case 9: cadastrarVeiculo(); break;    
                    case 10: cadastrarAluguel(); break;    
                    default: System.out.println("Opcao invalida. Tente novamente.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Nao foi possivel concluir: " + e.getMessage());
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n========== SISTEMA DE ALUGUEL ==========");
        System.out.println("1  - Associar veiculo a aluguel");
        System.out.println("2  - Exibir todos os veiculos");
        System.out.println("3  - Exibir todos os alugueis");
        System.out.println("4  - Exibir apenas veiculos disponiveis");
        System.out.println("5  - Avaliar veiculo ou aluguel");
        System.out.println("6  - Realizar inspecao");
        System.out.println("7  - Remover veiculos em manutencao");
        System.out.println("8  - Colocar veiculo em manutencao");
        System.out.println("9  - Cadastrar novo veiculo");
        System.out.println("10 - Cadastrar novo aluguel");
        System.out.println("0  - Sair");
    }

    private static void associarVeiculo() {
        System.out.println("\n--- ASSOCIAR VEICULO ---");
        exibirDisponiveis();
        if (SISTEMA.listarDisponiveis().isEmpty()) return;
        String placa = lerTexto("Placa do veiculo (0 cancela): ");
        if (placa.equals("0")) return;
        exibirAlugueis();
        int codigo = lerInteiro("Codigo do aluguel (0 cancela): ");
        if (codigo == 0) return;
        System.out.println("Informe o total de KM previstos para este veiculo durante o contrato.");
        int kmPrevistos = lerInteiro("KM previstos (0 se nao houver deslocamento): ");
        SISTEMA.associarVeiculo(placa, codigo, kmPrevistos);
        System.out.println("Veiculo associado com sucesso!");
    }

    private static void exibirVeiculos() {
        System.out.println("\n--- TODOS OS VEICULOS ---");
        List<Veiculo> veiculos = SISTEMA.getVeiculos();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veiculo cadastrado.");
        } else {
            for (Veiculo veiculo : veiculos) System.out.println(veiculo);
        }
        System.out.println("Total de veiculos: " + veiculos.size());
    }

    private static void exibirAlugueis() {
        System.out.println("\n--- TODOS OS ALUGUEIS ---");
        List<Aluguel> alugueis = SISTEMA.getAlugueis();
        if (alugueis.isEmpty()) {
            System.out.println("Nenhum aluguel cadastrado.");
        } else {
            for (Aluguel aluguel : alugueis) {
                System.out.println(aluguel);
                System.out.println("----------------------------------------");
            }
        }
        System.out.println("Total de alugueis: " + alugueis.size());
    }

    private static void exibirDisponiveis() {
        System.out.println("\n--- VEICULOS DISPONIVEIS ---");
        List<Veiculo> disponiveis = SISTEMA.listarDisponiveis();
        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum veiculo disponivel.");
        } else {
            for (Veiculo veiculo : disponiveis) System.out.println(veiculo);
        }
        System.out.println("Total disponivel: " + disponiveis.size());
    }

    private static void avaliarObjeto() {
        System.out.println("\n--- AVALIAR ---");
        System.out.println("1 - Veiculo (Carro ou Van)");
        System.out.println("2 - Aluguel (Executivo)");
        System.out.println("0 - Cancelar");
        int tipo = lerInteiro("Escolha: ");
        if (tipo == 0) return;
        if (tipo == 1) {
            exibirVeiculos();
            String placa = lerTexto("Placa do veiculo (0 cancela): ");
            if (placa.equals("0")) return;
            int nota = lerInteiro("Nota (0 a 10): ");
            SISTEMA.avaliarVeiculo(placa, nota);
            System.out.println("Veiculo avaliado com sucesso!");
        } else if (tipo == 2) {
            exibirAlugueis();
            int codigo = lerInteiro("Codigo do aluguel (0 cancela): ");
            if (codigo == 0) return;
            int nota = lerInteiro("Nota (0 a 10): ");
            SISTEMA.avaliarAluguel(codigo, nota);
            System.out.println("Aluguel avaliado com sucesso!");
        } else {
            System.out.println("Opcao de avaliacao invalida.");
        }
    }

    private static void realizarInspecao() {
        System.out.println("\n--- REALIZAR INSPECAO ---");
        List<Veiculo> inspecionaveis = SISTEMA.listarInspecionaveis();
        if (inspecionaveis.isEmpty()) {
            System.out.println("Nao ha veiculos aptos para inspecao.");
            return;
        }
        System.out.println("Escolha uma placa desta lista (Motos e Vans):");
        for (Veiculo veiculo : inspecionaveis) System.out.println(veiculo);
        String placa = lerTexto("Digite a placa para inspecionar (0 cancela): ");
        if (placa.equals("0")) return;
        SISTEMA.realizarInspecao(placa);
    }

    private static void removerManutencao() {
        System.out.println("\n--- REMOVER VEICULOS EM MANUTENCAO ---");
        int removidos = SISTEMA.removerEmManutencao();
        System.out.println("Quantidade removida: " + removidos);
    }

    private static void colocarEmManutencao() {
        System.out.println("\n--- COLOCAR EM MANUTENCAO ---");
        exibirVeiculos();
        String placa = lerTexto("Digite a placa (0 cancela): ");
        if (placa.equals("0")) return;
        SISTEMA.colocarEmManutencao(placa);
        System.out.println("Veiculo encaminhado para manutencao.");
    }

    private static void cadastrarVeiculo() {
        System.out.println("\n--- CADASTRAR VEICULO ---");
        System.out.println("1 - Carro | 2 - Moto | 3 - Van | 0 - Cancelar");
        int tipo = lerInteiro("Tipo: ");
        if (tipo == 0) return;
        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo de veiculo invalido.");
            return;
        }
        String placa = lerTexto("Placa: ");
        String modelo = lerTexto("Modelo: ");
        int ano = lerInteiro("Ano de fabricacao: ");
        double diaria = lerDecimal("Valor da diaria (R$): ");
        double km = lerDecimal("Quilometragem atual: ");
        Veiculo veiculo;
        if (tipo == 1) {
            veiculo = new Carro(placa, modelo, ano, diaria, km,
                    lerInteiro("Quantidade de portas: "));
        } else if (tipo == 2) {
            veiculo = new Moto(placa, modelo, ano, diaria, km,
                    lerInteiro("Cilindrada (cc): "));
        } else {
            veiculo = new Van(placa, modelo, ano, diaria, km,
                    lerInteiro("Maximo de passageiros: "));
        }
        SISTEMA.cadastrarVeiculo(veiculo);
        System.out.println("Veiculo cadastrado com sucesso!");
    }

    private static void cadastrarAluguel() {
        System.out.println("\n--- CADASTRAR ALUGUEL ---");
        System.out.println("1 - Basico | 2 - Premium | 3 - Executivo | 0 - Cancelar");
        int tipo = lerInteiro("Tipo: ");
        if (tipo == 0) return;
        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo de aluguel invalido.");
            return;
        }
        int codigo = lerInteiro("Codigo do aluguel: ");
        int dias = lerInteiro("Quantidade de dias: ");
        Aluguel aluguel;
        if (tipo == 1) aluguel = new AluguelBasico(codigo, dias);
        else if (tipo == 2) aluguel = new AluguelPremium(codigo, dias);
        else aluguel = new AluguelExecutivo(codigo, dias);
        SISTEMA.cadastrarAluguel(aluguel);
        System.out.println("Aluguel cadastrado com sucesso!");
    }

    private static class EntradaEncerrada extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }
}
