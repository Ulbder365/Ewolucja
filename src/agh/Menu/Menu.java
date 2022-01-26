package agh.Menu;

import javax.swing.*;

public class Menu {
    public JFrame menuFrame;

    public Menu() {
        menuFrame = new JFrame("Evolution Simulator (Settings)");
        menuFrame.setSize(500, 500);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);
    }
}

