package org.example;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import static javax.swing.JOptionPane.PLAIN_MESSAGE;

public class Interface extends JFrame {
    private JButton[][] botoes;
    private JPanel panelJogo;
    private JLabel jogadorUmLabel;
    private JLabel computadorLabel;
    private JLabel pontuacaoJogadorUmLabel;
    private JLabel pontuacaoComputadorLabel;

    private JLabel mostraVezLabel;
    private final JButton novo;

    private final Logica logica;

    // Método para gerar uma label auxiliar
    private JLabel geraLabelAuxiliar() {
        return new JLabel();
    }

    public Interface() {
        super("JOGO DA VELHA");
        configuraPanel();
        adicionaLabelJogadorUm();
        adicionaLabelPontuacaoJogadorUm();
        adicionaEspacoVazioNoPanel();
        adicionaLabelComputador();
        adicionaLabelPontuacaoComputador();
        adicionaEspacoVazioNoPanel();
        adicionaEspacoVazioNoPanel();
        adicionaLabelMostraVez();
        adicionaEspacoVazioNoPanel();

        logica = new Logica();

        configuraBotoes();

        adicionaEspacoVazioNoPanel();

        novo = new JButton("Novo Jogo");
        panelJogo.add(novo);
        novo.setPreferredSize(new Dimension(220, 90));
        novo.addActionListener((ActionEvent arg0) -> novoJogo());
    }

    private void configuraBotoes() {
        botoes = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton("");
                JButton jogadaButton = botoes[i][j];
                jogadaButton.setFont(new Font("ArialBlack", Font.BOLD, 70));
                int finalI = i;
                int finalJ = j;
                jogadaButton.addActionListener((ActionEvent e) -> {
                    String xOu0 = logica.jogada(mostraVezLabel);
                    jogadaButton.setText(xOu0);
                    jogadaButton.setEnabled(false);
                    verificacao(xOu0);

                    logica.removeJogada(finalI, finalJ);

                    if (mostraVezLabel.getText() == "Vez do Computador") {
                        int posicaoRandomica = logica.getPosicaoRandomica(logica.getJogadasEfetuadas());
                        if (posicaoRandomica == 0) {
                            botoes[0][0].doClick();
                        } else if (posicaoRandomica == 1) {
                            botoes[0][1].doClick();
                        } else if (posicaoRandomica == 2) {
                            botoes[0][2].doClick();
                        } else if (posicaoRandomica == 3) {
                            botoes[1][0].doClick();
                        } else if (posicaoRandomica == 4) {
                            botoes[1][1].doClick();
                        } else if (posicaoRandomica == 5) {
                            botoes[1][2].doClick();
                        } else if (posicaoRandomica == 6) {
                            botoes[2][0].doClick();
                        } else if (posicaoRandomica == 7) {
                            botoes[2][1].doClick();
                        } else if (posicaoRandomica == 8) {
                            botoes[2][2].doClick();
                        }
                    }

                });
                panelJogo.add(jogadaButton);
                jogadaButton.setPreferredSize(new Dimension(11, 110));
            }
        }
    }

    private void configuraPanel() {
        panelJogo = new JPanel(new BorderLayout());
        JOptionPane pane = new JOptionPane(panelJogo, PLAIN_MESSAGE, PLAIN_MESSAGE);
        add(pane);
        panelJogo.setLayout(new GridLayout(7, 3, 5, 5));
        setVisible(true);
    }

    private void adicionaLabelMostraVez() {
        mostraVezLabel = new JLabel("Vez do Jogador 1");
        panelJogo.add(mostraVezLabel);
    }

    private void adicionaEspacoVazioNoPanel() {
        panelJogo.add(geraLabelAuxiliar());
    }

    private void adicionaLabelJogadorUm() {
        jogadorUmLabel = new JLabel("Jogador 1");
        panelJogo.add(jogadorUmLabel);
    }

    private void adicionaLabelComputador() {
        computadorLabel = new JLabel("Computador");
        panelJogo.add(computadorLabel);
    }

    private void adicionaLabelPontuacaoJogadorUm() {
        pontuacaoJogadorUmLabel = new JLabel();
        pontuacaoJogadorUmLabel.setText("0");
        panelJogo.add(pontuacaoJogadorUmLabel);
    }

    private void adicionaLabelPontuacaoComputador() {
        pontuacaoComputadorLabel = new JLabel();
        pontuacaoComputadorLabel.setText("0");
        panelJogo.add(pontuacaoComputadorLabel);
    }

    public void verificacao(String xOu0) {
        String[][] jogadas = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                jogadas[i][j] = botoes[i][j].getText();
            }
        }
        Boolean verificacao = logica.verificacao(xOu0, jogadas, mostraVezLabel);

        atualizaPontuacao();
        novo.setVisible(true);

        if (verificacao != null) {
            if (!verificacao) mostraDeuVelha();
            else {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        botoes[i][j].setEnabled(false);
                    }
                }
            }
        }
    }

    private void atualizaPontuacao() {
        pontuacaoJogadorUmLabel.setText(logica.getPontuacaoJogadorUm());
        pontuacaoComputadorLabel.setText(logica.getPontuacaoComputador());
    }

    public void mostraDeuVelha() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setEnabled(false);
            }
            novo.setVisible(true);
            mostraVezLabel.setText("Deu Velha");

        }
    }

    public void novoJogo() {
        novo.setVisible(true);
        logica.novoJogo();
        mostraVezLabel.setText("Vez do Jogador 1");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText("");
                botoes[i][j].setBackground(novo.getBackground());
                botoes[i][j].setEnabled(true);
            }
        }
    }
}