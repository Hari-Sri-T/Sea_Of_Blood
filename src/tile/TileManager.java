package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world1.txt");

    }

    private void getTileImage() {


        setup(0,"grass",false);
        setup(1,"wall",true);
        setup(2,"water",true);



    }
    public void setup(int index, String imagePath, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imagePath+".png"));
            tile[index].image = uTool.scaleImage(tile[index].image,gp.tileSize,gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int row = 0; row < gp.maxWorldRow; row++) {
                String line = br.readLine();
                if (line == null) break; // Defensive coding

                String[] numbers = line.trim().split("\\s+");

                for (int col = 0; col < gp.maxWorldCol; col++) {
                    if (col < numbers.length) {
                        int num = Integer.parseInt(numbers[col]);
                        mapTileNum[col][row] = num;
                    } else {
                        // Optional: Fill remaining cells with 0 or default
                        mapTileNum[col][row] = 0;
                    }
                }
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace(); // Shows real error
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while(worldRow < gp.maxWorldRow && worldCol < gp.maxWorldCol){

            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(gp.player.screenX > gp.player.worldX){
                screenX=worldX;
            }
            if(gp.player.screenY > gp.player.worldY){
                screenY=worldY;
            }
            int rightOffset = gp.screenWidth - gp.player.screenX;

            if(rightOffset > gp.worldWidth - gp.player.worldX){
                screenX = gp.screenWidth - (gp.worldWidth - worldX);
            }

            int bottomOffSet = gp.screenHeight - gp.player.screenY;
            if(bottomOffSet > gp.worldHeight - gp.player.worldY){
                screenY = gp.screenHeight - (gp.worldHeight - worldY);
            }


            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize< gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image,screenX,screenY,null);

            }

            else if(gp.player.screenX > gp.player.worldX ||
                    gp.player.screenY > gp.player.worldY ||
                    rightOffset > gp.worldWidth - gp.player.worldX ||
                    bottomOffSet > gp.worldHeight - gp.player.worldY){
                g2.drawImage(tile[tileNum].image,screenX,screenY,null);

            }
            worldCol++;



            if(worldCol == gp.maxWorldCol){
                worldCol = 0;

                worldRow++;

            }

        }

    }


}
