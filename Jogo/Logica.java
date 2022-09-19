package org.example;

import javax.swing.JLabel;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Logica {

    private int vezJogador = 0;
    private final static String VEZ_JOGADOR_UM = "Vez do Jogador 1";
    private final static String VEZ_COMPUTADOR = "Vez do Computador";
    private final static String JOGADOR_UM_VENCEDOR = "Jogador 1 Venceu";
    private final static String COMPUTADOR = "Computador Venceu";

    public List<Integer> getJogadasEfetuadas() {
        return jogadasEfetuadas;
    }

    private static List<Integer> jogadasEfetuadas = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);

    public void novoJogo() {
        vezJogador = 0;
        iniciaJogadas();
    }

    private void iniciaJogadas() {
        jogadasEfetuadas = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
    }

    public static void removeJogada(int linha, int coluna) {
        if (linha == 0 && coluna == 0) {
            jogadasEfetuadas = jogadasEfetuadas.stream().filter(integer -> integer != 0).collect(Collectors.toList());
        } else if (linha == 0 && coluna == 1) {
            jogadasEfetuadas = jogadasEfetuadas.stream().filter(integer -> integer != 1).collect(Collectors.toList());
        } else if (linha == 0 && coluna == 2) {
            jogadasEfetuadas = jogadasEfetuadas.stream().filter(integer -> integer != 2).collect(Collectors.toList());
        } else if (linha == 1 && coluna == 0) {
            jogadasEfetuadas = jogadasEfetuadas.stream().filter(integer -> integer != 3).collect(Collectors.toList());
        } else if (linha == 1 && coluna == 1) {
            jogadasEfetuadas = jogadasEfetuadas.stream().filter(integer -> integer != 4).collect(Collectors.toList());
        } else if (linha == 1 && coluna == 2) {
            jogadasEfetuadas = jogadasEfetuadas.stream().filter(integer -> integer != 5).collect(Collectors.toList());
        } else if (linha == 2 && coluna == 0) {
            jogadasEfetuadas = jogadasEfetuadas.stream().filter(integer -> integer != 6).collect(Collectors.toList());
        } else if (linha == 2 && coluna == 1) {
            jogadasEfetuadas = jogadasEfetuadas.stream().filter(integer -> integer != 7).collect(Collectors.toList());
        } else if (linha == 2 && coluna == 2) {
            jogadasEfetuadas = jogadasEfetuadas.stream().filter(integer -> integer != 8).collect(Collectors.toList());
        }
    }

    public int getPosicaoRandomica(List<Integer> jogadasEfetuadas) {
        Random rand = new Random();
        return jogadasEfetuadas.get(rand.nextInt(jogadasEfetuadas.size()));
    }

    public String getPontuacaoJogadorUm() {
        return Integer.toString(pontuacaoJogadorUm);
    }

    public String getPontuacaoComputador() {
        return Integer.toString(pontuacaoComputador);
    }

    private int pontuacaoJogadorUm = 0;
    private int pontuacaoComputador = 0;

    public String jogada(JLabel label) {
        String opcao;
        if (vezJogador == 0) {
            opcao = " X ";
            vezJogador = 1;
            label.setText(VEZ_COMPUTADOR);

        } else {
            opcao = " 0 ";
            vezJogador = 0;
            label.setText(VEZ_JOGADOR_UM);
        }
        return opcao;
    }

    public Boolean verificacao(String jogador, String[][] jogadas, JLabel label) {
        if ((jogadas[0][0].equals(jogador)) && (jogadas[1][1].equals(jogador)) && (jogadas[2][2].equals(jogador))) {
            // Diagonal
            incrementaPontos(jogador, label);
            return true;
        } else if ((jogadas[0][2].equals(jogador)) && (jogadas[1][1].equals(jogador)) && (jogadas[2][0].equals(jogador))) {
            // Diagonal
            incrementaPontos(jogador, label);
            return true;
        } else if ((jogadas[0][0].equals(jogador)) && (jogadas[0][1].equals(jogador)) && (jogadas[0][2].equals(jogador))) {
            // Horizontal
            incrementaPontos(jogador, label);
            return true;
        } else if ((jogadas[1][0].equals(jogador)) && (jogadas[1][1].equals(jogador)) && (jogadas[1][2].equals(jogador))) {
            // Horizontal
            incrementaPontos(jogador, label);
            return true;
        } else if ((jogadas[2][0].equals(jogador)) && (jogadas[2][1].equals(jogador)) && (jogadas[2][2].equals(jogador))) {
            // Horizontal
            incrementaPontos(jogador, label);
            return true;
        } else if ((jogadas[0][0].equals(jogador)) && (jogadas[1][0].equals(jogador)) && (jogadas[2][0].equals(jogador))) {
            // Vertical
            incrementaPontos(jogador, label);
            return true;
        } else if ((jogadas[0][1].equals(jogador)) && (jogadas[1][1].equals(jogador)) && (jogadas[2][1].equals(jogador))) {
            // Vertical
            incrementaPontos(jogador, label);
            return true;
        } else if ((jogadas[0][2].equals(jogador)) && (jogadas[1][2].equals(jogador)) && (jogadas[2][2].equals(jogador))) {
            // Vertical
            incrementaPontos(jogador, label);
            return true;
        } else
            //veriifca se deu velha
            if ((!"".equals(jogadas[0][0])) && (!"".equals(jogadas[0][1])) && (!"".equals(jogadas[0][2])) && (!"".equals(jogadas[1][0])) && (!"".equals(jogadas[1][1])) && (!"".equals(jogadas[1][2])) && (!"".equals(jogadas[2][0])) && (!"".equals(jogadas[2][1])) && !"".equals(jogadas[2][2])) {
                return false;
            }
        return null;
    }

    private void incrementaPontos(String jogador, JLabel label) {
        if (" X ".equals(jogador)) {
            pontuacaoJogadorUm++;
            label.setText(JOGADOR_UM_VENCEDOR);
        } else {
            pontuacaoComputador++;
            label.setText(COMPUTADOR);
        }
    }

}
