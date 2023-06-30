package AlyssonVeiculo;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProdutoDAO produtoDAO = new ProdutoDAO();

        int opcao;
        do {
            System.out.println("======= MENU =======");
            System.out.println("1. Inserir veículo");
            System.out.println("2. Consultar veículo por ID");
            System.out.println("3. Consultar veículo por placa");
            System.out.println("4. Excluir veículo");
            System.out.println("5. Atualizar veículo");
            System.out.println("0. Sair");
            System.out.println("====================");
            System.out.print("Digite a opção desejada: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    System.out.println("===== INSERIR VEÍCULO =====");
                    Veiculo veiculoInserir = obterDadosVeiculo(scanner);
                    produtoDAO.inserir(veiculoInserir);
                    System.out.println("Veículo inserido com sucesso!");
                    System.out.println("===========================");
                    break;
                case 2:
                    System.out.println("===== CONSULTAR VEÍCULO POR ID =====");
                    System.out.print("Digite o ID do veículo: ");
                    int idConsultar = scanner.nextInt();
                    Veiculo veiculoConsultadoId = produtoDAO.consultarID(idConsultar);
                    if (veiculoConsultadoId != null) {
                        System.out.println("Veículo consultado por ID: " + veiculoConsultadoId);
                    } else {
                        System.out.println("Nenhum veículo encontrado com o ID informado.");
                    }
                    System.out.println("=====================================");
                    break;
                case 3:
                    System.out.println("===== CONSULTAR VEÍCULO POR PLACA =====");
                    System.out.print("Digite a placa do veículo: ");
                    String placaConsultar = scanner.nextLine();
                    Veiculo veiculoConsultadoPlaca = produtoDAO.consultarPlaca(placaConsultar);
                    if (veiculoConsultadoPlaca != null) {
                        System.out.println("Veículo consultado por placa: " + veiculoConsultadoPlaca);
                    } else {
                        System.out.println("Nenhum veículo encontrado com a placa informada.");
                    }
                    System.out.println("=======================================");
                    break;
                case 4:
                    System.out.println("===== EXCLUIR VEÍCULO =====");
                    System.out.print("Digite o ID do veículo a ser excluído: ");
                    int idExcluir = scanner.nextInt();
                    produtoDAO.excluir(idExcluir);
                    System.out.println("Veículo excluído com sucesso!");
                    System.out.println("===========================");
                    break;
                case 5:
                    System.out.println("===== ATUALIZAR VEÍCULO =====");
                    System.out.print("Digite o ID do veículo a ser atualizado: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer do scanner
                    Veiculo veiculoAtualizar = obterDadosVeiculo(scanner);
                    veiculoAtualizar.setId(idAtualizar);
                    produtoDAO.atualizar(veiculoAtualizar);
                    System.out.println("Veículo atualizado com sucesso!");
                    System.out.println("=============================");
                    break;
                case 0:
                    System.out.println("Encerrando o programa. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Digite um número válido.");
                    System.out.println("======================================");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static Veiculo obterDadosVeiculo(Scanner scanner) {
        System.out.print("Digite o número do chassi: ");
        String numeroChassi = scanner.nextLine();
        System.out.print("Digite a placa: ");
        String placa = scanner.nextLine();
        System.out.print("Digite o modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer do scanner

        return new Veiculo(numeroChassi, placa, modelo, nome, valor);
    }
}
