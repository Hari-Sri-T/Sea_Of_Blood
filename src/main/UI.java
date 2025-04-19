package main;

import object.Obj_key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;


public class UI {

    GamePanel gp;
    Font arial_30, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");



    public UI(GamePanel gp){
        this.gp = gp;
        arial_30 = new Font("Arial",Font.PLAIN,30);
        arial_80B = new Font("Arial",Font.BOLD,80);

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
            int x;
            int y;

            text = " You have found the Treasure!!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2-(gp.tileSize * 3);
            g2.drawString(text,x,y);

            text = " Youe Time is :"+dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize * 4);
            g2.drawString(text,x,y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);

            text = "Congratulations!!!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize * 3);
            g2.drawString(text,x,y);

            gp.gameThread = null;

        }
        else {
            g2.setFont(arial_30);
            g2.setColor(Color.white);
            g2.drawImage(keyImage,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
            g2.drawString("x "+gp.player.hasKey,70,62);


            //TIME
            playTime += (double)1/60;
            g2.drawString("Time : "+dFormat.format(playTime),gp.tileSize*11,65);


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
