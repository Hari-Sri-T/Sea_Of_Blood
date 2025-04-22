package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public boolean hasSword = false;
    public boolean attacking = false;




    public Player(GamePanel gp,KeyHandler keyH){

        super(gp);

        this.keyH = keyH;
        screenX = gp.screenWidth/2-(gp.tileSize/2);
        screenY = gp.screenHeight/2-(gp.tileSize/2);
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize *26;
        worldY = gp.tileSize *25;
        speed = 3;
        direction = "down";
    }
    public void getPlayerImage(){

        up1 = setup("/res/player/c_up_1.png");
        up2 = setup("/res/player/c_up_2.png");
        down1 = setup("/res/player/c_down_1.png");
        down2 = setup("/res/player/c_down_2.png");
        right1 = setup("/res/player/c_right_1.png");
        right2 = setup("/res/player/c_right_2.png");
        left1 = setup("/res/player/c_left_1.png");
        left2 = setup("/res/player/c_left_2.png");
    }
    public void getPlayerSwordImage() {
        e_up1    = setup("/res/player/pe_up_1.png");
        e_up2    = setup("/res/player/pe_up_2.png");
        e_down1  = setup("/res/player/pe_down_1.png");
        e_down2  = setup("/res/player/pe_down_2.png");
        e_right1 = setup("/res/player/pe_right_1.png");
        e_right2 = setup("/res/player/pe_right_2.png");
        e_left1  = setup("/res/player/pe_left_1.png");
        e_left2  = setup("/res/player/pe_left_2.png");
    }
    

   
    

    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true ) {
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);


            //Check object Collision
            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            int npcIndex = gp.cChecker.checkEntity(this,gp.npc);
            interactNPC(npcIndex)
;
            if(collisionOn == false){
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;
    
            switch(objectName) {
                case "Sword":
                    hasSword = true;
                    getPlayerSwordImage(); // Load sword sprites
                    gp.obj[i] = null; // Remove the sword from the map
                    break;
            }
        }
    }
    

    public void interactNPC(int i){
        if(i != 999){
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyH.enterPressed = false;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
    
        boolean equipped = hasSword;
    
        switch (direction) {
            case "up":
                image = (spriteNum == 1) ? (equipped ? e_up1 : up1) : (equipped ? e_up2 : up2);
                break;
            case "down":
                image = (spriteNum == 1) ? (equipped ? e_down1 : down1) : (equipped ? e_down2 : down2);
                break;
            case "left":
                image = (spriteNum == 1) ? (equipped ? e_left1 : left1) : (equipped ? e_left2 : left2);
                break;
            case "right":
                image = (spriteNum == 1) ? (equipped ? e_right1 : right1) : (equipped ? e_right2 : right2);
                break;
        }
    
        int x = screenX;
        int y = screenY;
    
        if (screenX > worldX) x = worldX;
        if (screenY > worldY) y = worldY;
        int rightOffset = gp.screenWidth - screenX;
        if (rightOffset > gp.worldWidth - worldX) x = gp.screenWidth - (gp.worldWidth - worldX);
        int bottomOffset = gp.screenHeight - screenY;
        if (bottomOffset > gp.worldHeight - worldY) y = gp.screenHeight - (gp.worldHeight - worldY);
    
        g2.drawImage(image, x, y, null);
    }
    

}
