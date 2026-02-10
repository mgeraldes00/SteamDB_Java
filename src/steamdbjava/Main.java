/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package steamdbjava;

/**
 *
 * @author fake2
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        JogoMng repo = new JogoMng();
        repo.carregarDeCSV("jogos.csv");

        boolean ativo = true;
        while (ativo) {
            System.out.println("\n=== BASE DE DADOS DE VIDEOJOGOS ===");
            System.out.println("1. Listar alguns jogos");
            System.out.println("2. Pesquisar por título");
            System.out.println("3. Filtrar por avaliação mínima (Metacritic)");
            System.out.println("4. Filtrar por categoria (Single/Multi/Coop/VR)");
            System.out.println("0. Sair");
            System.out.print("Opção: ");

            String opcaoStr = sc.nextLine();
            int opcao;
            try {
                opcao = Integer.parseInt(opcaoStr);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {
                case 1:
                    listarAlguns(repo.getJogos());
                    break;
                case 2:
                    pesquisarTitulo(sc, repo);
                    break;
                case 3:
                    filtrarMetacritic(sc, repo);
                    break;
                case 4:
                    filtrarCategoria(sc, repo);
                    break;
                case 0:
                    ativo = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }

    private static void listarAlguns(List<Jogo> jogos) {
        int limite = Math.min(20, jogos.size());
        for (int i = 0; i < limite; i++) {
            System.out.println(jogos.get(i));
        }
    }

    private static void pesquisarTitulo(Scanner sc, JogoMng repo) {
        System.out.print("Título a pesquisar: ");
        String termo = sc.nextLine();
        List<Jogo> res = repo.pesquisarPorTitulo(termo);
        if (res.isEmpty()) System.out.println("Nenhum jogo encontrado.");
        else res.forEach(System.out::println);
    }

    private static void filtrarMetacritic(Scanner sc, JogoMng repo) {
        System.out.print("Metacritic mínimo: ");
        try {
            int minimo = Integer.parseInt(sc.nextLine());
            List<Jogo> res = repo.filtrarPorMetacritic(minimo);
            if (res.isEmpty()) System.out.println("Nenhum jogo encontrado.");
            else res.forEach(System.out::println);
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
        }
    }

    private static void filtrarCategoria(Scanner sc, JogoMng repo) {
        System.out.print("Categoria (single/multi/coop/vr): ");
        String cat = sc.nextLine();
        List<Jogo> res = repo.filtrarPorCategoria(cat);
        if (res.isEmpty()) System.out.println("Nenhum jogo encontrado.");
        else res.forEach(System.out::println);
    }
}
