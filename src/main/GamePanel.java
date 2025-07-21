package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private Random random;
    private float xDelta= 100;
    private float yDelta = 150;
    private float yDir = 1;
    private float xDir = 1;
    private Color color = new Color(120,23,21);

    public GamePanel(){
        mouseInputs = new MouseInputs(this);
        random = new Random();

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }
    public void changeXDelta (int value){
        this.xDelta += value;
    }
    public void changeYDelta (int value){
        this.yDelta += value;
    }
    public void setRectPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        updateRectangle();
        g.setColor(color);
        g.fillRect((int)xDelta,(int) yDelta,200,50);


    }

    private void updateRectangle() {
        xDelta += xDir;
        if(xDelta > 200 || xDelta < 0){
            xDir *= -1;
            color = getRndColor();
        }
        yDelta += yDir;
        if(yDelta > 310 || yDelta < 0){
            yDir *= -1;
            color = getRndColor();
        }
    }

    private Color getRndColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return new Color(r,g,b);
    }
}
