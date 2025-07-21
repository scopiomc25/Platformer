package main;

import inputs.KeyboardInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{

    public GamePanel(){
        new KeyboardInputs();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.fillRect(100,150,200,50);
    }
}
