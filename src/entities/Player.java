package entities;

import utils.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static main.Game.SCALE;
import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.*;

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
    private int[][] lvlData;
    private float xDrawOffset = 21 * SCALE;
    private float yDrawOffset = 3 * SCALE;


    //Jumping / Gravity Variables
    private boolean jump;
    private float airSpeed = 0f;
    private float gravity = 0.2f * SCALE;
    private float jumpSpeed = 2.25f * SCALE;
    private float fallSpeedAfterCollision = 0.5f * SCALE;
    private boolean inAir = false;


    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitBox(x, y, 20 * SCALE, 28 * SCALE);
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {

        g.drawImage(animations[playerAction][animationIndex], (int) (hitBox.x - xDrawOffset),
                (int) (hitBox.y - yDrawOffset), width, height, null);
        //drawHitBox(g); //Just for DEBUG purpose

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
        if(!inAir){
            if(!isEntityOnFloor(hitBox, lvlData)){
                inAir = true;
            }
        }
        if (jump){
            jump();
        }

        if (!left && !right && !inAir) {
            return;
        }

        float xSpeed = 0;


        if (left) {
            xSpeed -= playerSpeed;
        }
        if (right) {
            xSpeed += playerSpeed;
        }

        if (inAir) {
            if (canMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, lvlData)) {
                hitBox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else {
                hitBox.y = GetEntityYPosUnderRoofOrAboveFloor(hitBox, airSpeed);
                if (airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = fallSpeedAfterCollision;
                }
                updateXPos(xSpeed);
            }
        } else {
            updateXPos(xSpeed);
        }
        moving = true;


    }


    private void jump() {
        if(inAir){
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (canMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width,
                hitBox.height, lvlData)) {
            hitBox.x += xSpeed;
        } else {
            hitBox.x = getEntityXPosNextToWall(hitBox, xSpeed);
        }
    }

    private void setAnimation() {
        int startAnimation = playerAction;

        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
        if(inAir){
            if(airSpeed < 0){
                playerAction = JUMPING;
            }else{
                playerAction = FALLING;
            }
        }

        if (attacking) {
            playerAction = HITTING;
        }
        if (startAnimation != playerAction) {
            resetAnimationTick();
        }
    }

    private void resetAnimationTick() {
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

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
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
    public boolean getInAir(){
        return inAir;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setJump(boolean jump){
        this.jump = jump;
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
