package org.example;

import javax.swing.*;

public class App extends JFrame {
    public static void main(String[] args) {
        Interface Tela = new Interface();
        Tela.setBounds(300, 80, 1080, 720);
        Tela.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Tela.setResizable(false);
    }
}