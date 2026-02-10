package steamdbjava;

import java.io.*;
import java.util.*;

public class JogoMng {
    private List<Jogo> jogos = new ArrayList<>();

    public void carregarDeCSV(String caminho) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha = br.readLine(); // cabeçalho
            while ((linha = br.readLine()) != null) {
                Jogo jogo = parseLinha(linha);
                if (jogo != null) {
                    jogos.add(jogo);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler ficheiro: " + e.getMessage());
        }
    }

    private Jogo parseLinha(String linha) {
        // Versão simples: assume que AboutText não tem vírgulas, ou está tratada
        String[] partes = linha.split(",", -1);
        if (partes.length < 11) return null;

        try {
            int id = Integer.parseInt(partes[0]);
            String name = partes[1];
            String releaseDate = partes[2];
            int requiredAge = Integer.parseInt(partes[3].isEmpty() ? "0" : partes[3]);
            int metacritic = Integer.parseInt(partes[4].isEmpty() ? "0" : partes[4]);
            boolean catSingle = Boolean.parseBoolean(partes[5]);
            boolean catMulti = Boolean.parseBoolean(partes[6]);
            boolean catCoop = Boolean.parseBoolean(partes[7]);
            boolean catVR = Boolean.parseBoolean(partes[8]);
            String about = partes[9];
            String headerImage = partes[10];

            return new Jogo(id, name, releaseDate, requiredAge, metacritic,
                    catSingle, catMulti, catCoop, catVR, about, headerImage);
        } catch (NumberFormatException e) {
            System.out.println("Linha inválida (ignorada): " + linha);
            return null;
        }
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public List<Jogo> pesquisarPorTitulo(String termo) {
        List<Jogo> res = new ArrayList<>();
        String t = termo.toLowerCase();
        for (Jogo j : jogos) {
            if (j.getNome().toLowerCase().contains(t)) {
                res.add(j);
            }
        }
        return res;
    }

    public List<Jogo> filtrarPorMetacritic(int minimo) {
        List<Jogo> res = new ArrayList<>();
        for (Jogo j : jogos) {
            if (j.getMetacritic() >= minimo) {
                res.add(j);
            }
        }
        return res;
    }

    public List<Jogo> filtrarPorCategoria(String categoria) {
        List<Jogo> res = new ArrayList<>();
        for (Jogo j : jogos) {
            switch (categoria.toLowerCase()) {
                case "single":
                    if (j.isSinglePlayer()) res.add(j);
                    break;
                case "multi":
                    if (j.isMultiplayer()) res.add(j);
                    break;
                case "coop":
                    if (j.isCoop()) res.add(j);
                    break;
                case "vr":
                    if (j.isVrSupport()) res.add(j);
                    break;
            }
        }
        return res;
    }
}
