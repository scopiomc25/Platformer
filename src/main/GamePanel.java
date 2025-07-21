package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this);
        this.game = game;

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }


    private void setPanelSize() {
        Dimension size = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("Height: " + Game.GAME_HEIGHT + " | Width: " + Game.GAME_WIDTH);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);
    }

    public void updateGame() {
    }

    public Game getGame() {
        return game;
    }

}
