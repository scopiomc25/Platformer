package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;


public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 0;
    private float yDelta = 0;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animationTick;
    private int animationIndex;
    private int animationSpeed= 25;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;


    public GamePanel() {
        mouseInputs = new MouseInputs(this);

        importImage();
        loadAnimations();

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void loadAnimations() {
        animations = new BufferedImage[7][8];
        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = img.getSubimage(j*64, i*40,64,40);
            }
        }
    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/res/player_sprites.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setDirection(int direction){
        this.playerDir = direction;
        moving=true;
    }
    public void setMoving(boolean moving){
        this.moving = moving;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(animations[playerAction][animationIndex], (int) xDelta, (int) yDelta, 256, 160, null);

    }

    private void updatePos() {
        if(moving){
            switch (playerDir){
                case LEFT:
                    xDelta -= 3;
                    break;
                case UP:
                    yDelta -= 3;
                    break;
                case RIGHT:
                    xDelta += 3;
                    break;
                case DOWN:
                    yDelta += 3;
                    break;

            }
        }
    }
    public void updateGame(){
        updateAnimationTick();
        setAnimation();
        updatePos();
    }

    private void setAnimation() {
        if(moving){
            playerAction = RUNNING;
        }else {
            playerAction = IDLE;
        }
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpriteAmount(playerAction)){
                animationIndex = 0;
            }
        }
    }

}
