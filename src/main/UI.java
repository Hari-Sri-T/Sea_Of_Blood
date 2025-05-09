package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import java.io.InputStream;
import java.io.IOException;

import object_java.Obj_key;


public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font purisaB;
    Font maruMonica;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;



    public UI(GamePanel gp){
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.out.println("Error loading fonts.");
        }



    }
    public void showMessage(String text){
        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(purisaB.deriveFont(Font.PLAIN,35F));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        // TITLE STATE
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        // PLAY STATE
        if(gp.gameState == gp.playState){

        }
        // PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPauseScreeen();
        }
        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }

    }
   

    public void drawTitleScreen() {
        BufferedImage titleBackground = null;
        try {
            titleBackground = ImageIO.read(getClass().getResourceAsStream("/res/title/Final Title Screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // Draw background
        if (titleBackground != null) {
            g2.drawImage(titleBackground, 0, 0, gp.screenWidth, gp.screenHeight, null);
        } else {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        }
    
       
        int x ;
        int y = gp.tileSize * 6;
       
    
        // Menu Options
        g2.setFont(maruMonica.deriveFont(Font.BOLD, 40F));
    
        String[] options = {"Play", "Exit"};
        y += gp.tileSize * 2;
    
        for (int i = 0; i < options.length; i++) {
            x = getXforCentredText(options[i]);
            if (i == commandNum) {
                g2.setColor(Color.RED);
            } else {
                g2.setColor(Color.WHITE);
            }
            g2.drawString(options[i], x, y + i * 50);
        }
    }
    
    
    public void drawPauseScreeen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        String text = "PAUSED";
        int x;
        int y;
        x = getXforCentredText(text);
        y = gp.screenHeight/2;

        g2.drawString(text,x,y);

    }
    public void drawDialogueScreen(){
        // Window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x,y,width,height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
        x += gp.tileSize;
        y += gp.tileSize;
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y += 40;
        }
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height,35 , 35);
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    public int getXforCentredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2-length/2;
        return  x;
    }
}
