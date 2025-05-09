package object_java;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_chest extends SuperObject{
    GamePanel gp;
    public Obj_chest(GamePanel gp){
        this.gp=gp;
        name="Chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/object/chest.png"));
            uTool.scaleImage(image, gp.tileSize,gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
