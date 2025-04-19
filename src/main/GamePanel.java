package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //Screen Settings
    final int originalTileSize = 16;
    final int scale = 3;

    int fps = 60;
    //16*16 is too small for our monitors so scaling is required

    public final int tileSize = originalTileSize * scale; //Single 48 pixel tile

    //Screen Size

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow;//576 pixels
    //Creating a Clock to keep Game Updating
    Thread gameThread;
    TileManager tileM = new TileManager(this);

    public UI ui = new UI(this);
    KeyHandler keyH = new KeyHandler();

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[10];


    //World Setting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;




    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        player.setDefaultValues();
        player.getPlayerImage();

    }

    public void setupGame(){
        aSetter.setObject();
    }


    public void startGameThread(){
        gameThread = new Thread(this);// Passing GamePanel Class here
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }




        }

    }
    public void update(){
        player.update();

    }

    //paintComponent is an exisiting Method in Java , With graphics as standard class
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        for(int i = 0 ; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2,this);
            }
        }
        player.draw(g2);

        ui.draw(g2);
        g2.dispose();

    }


}
