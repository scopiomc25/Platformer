package main;

import javax.swing.*;

public class GameWindow {

    private JFrame jFrame;

    public GameWindow(GamePanel gamePanel) {
        jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.pack();
        jFrame.setTitle("Platformer Game");
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);

        jFrame.setVisible(true);
    }
}
