package entities;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.SCALE;
import static utils.Constants.PlayerConstants.*;

public class Player extends Entity {


    private BufferedImage[][] animations;

    private int animationTick;
    private int animationIndex;
    private int animationSpeed = 25;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;
    private boolean attacking = false;
    private boolean left;
    private boolean up;
    private boolean right;
    private boolean down;
    private float playerSpeed = 0.5f * SCALE;
    private int width;
    private int height;

    public Player(float x, float y, int sizeWidth, int sizeHeight) {
        super(x, y);
        loadAnimations();
        this.width = sizeWidth;
        this.height = sizeHeight;
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {

        g.drawImage(animations[playerAction][animationIndex], (int) x, (int) y, width, height, null);

    }


    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= getSpriteAmount(playerAction)) {
                animationIndex = 0;
                attacking = false;
            }
        }
    }

    private void updatePos() {
        moving = false;
        if (left && !right) {
            moving = true;
            x -= playerSpeed;
        } else if (!left && right) {
            moving = true;
            x += playerSpeed;
        }
        if (up && !down) {
            moving = true;
            y -= playerSpeed;
        } else if (!up && down) {
            moving = true;
            y += playerSpeed;
        }
    }

    private void setAnimation() {
        int startAnimation = playerAction;

        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }

        if (attacking) {
            playerAction = HITTING;
        }
        if (startAnimation != playerAction) {
            resetAnimationTink();
        }
    }

    private void resetAnimationTink() {
        animationIndex = 0;
        animationTick = 0;
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[7][8];
        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = img.getSubimage(j * 64, i * 40, 64, 40);
            }
        }
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void resetDirBooleans() {
        left = false;
        up = false;
        down = false;
        right = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
}
