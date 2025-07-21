package main;

import javax.swing.*;

public class GameWindow{

    private JFrame jFrame;

    public GameWindow(GamePanel gamePanel){
        jFrame = new JFrame();

        jFrame.setSize(400,400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setTitle("Platformer Game");

        jFrame.add(gamePanel);

        jFrame.setVisible(true);
    }
}
