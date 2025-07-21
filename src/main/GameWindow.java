package main;

import utils.LoadSave;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;

public class GameWindow {

    private JFrame jFrame;
    private BufferedImage img;

    public GameWindow(GamePanel gamePanel) {
        img = LoadSave.GetSpriteAtlas("ball.png");

        jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.pack();
        jFrame.setTitle("Platformer Game");
        jFrame.setIconImage(img);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);

        jFrame.setVisible(true);
        jFrame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }
        });
    }
}
