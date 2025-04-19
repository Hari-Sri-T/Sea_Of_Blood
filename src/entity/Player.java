package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    public Player(GamePanel gp,KeyHandler keyH){
        this.gp = gp;
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
        worldX = gp.tileSize *10;
        worldY = gp.tileSize *10;
        speed = 3;
        direction = "down";
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/c_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/c_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/c_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/c_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/c_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/c_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/c_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/c_left_2.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }
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

    public void pickUpObject(int  i){
        if(i != 999){

            String objectName = gp.obj[i].name;

            switch(objectName){
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a Key!!");
                    break;
                case "Door":
                    if(hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened a Door!!");
                    }
                    else {
                        gp.ui.showMessage("Not enough Keys!!");
                    }

                case "Chest":
                    gp.ui.gameFinished = true;





            }
        }

    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;

            case "down" :
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left" :
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right" :
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;

        };

        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }

}
