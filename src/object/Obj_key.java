package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;


public class Obj_key extends SuperObject{
    GamePanel gp;
    public Obj_key(GamePanel gp){
        this.gp=gp;
        name="Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/object/key.png"));
            uTool.scaleImage(image, gp.tileSize,gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
