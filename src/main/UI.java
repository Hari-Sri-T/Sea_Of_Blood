package main;

import object.Obj_key;

import java.awt.*;
import java.awt.image.BufferedImage;


public class UI {

    GamePanel gp;
    Font arial_30;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public boolean gameFinished = false;


    public UI(GamePanel gp){
        this.gp = gp;
        arial_30 = new Font("Arial",Font.PLAIN,30);
        Obj_key key = new Obj_key();
        keyImage = key.image;

    }
    public void showMessage(String text){
        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2){

        if(gameFinished == true){
            String text;
            int textLength;

            g2.setFont(arial_30);
            g2.setColor(Color.white);


            int x = gp.screenWidth/2;
            int y = gp.screenHeight/2;

        }
        else {
            g2.setFont(arial_30);
            g2.setColor(Color.white);
            g2.drawImage(keyImage,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
            g2.drawString("x "+gp.player.hasKey,70,62);

            if(messageOn == true){
                g2.setFont(g2.getFont().deriveFont(20F));
                g2.drawString(message,gp.tileSize*7,gp.tileSize*8);
                messageCounter++;

                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }

        }

    }
}
