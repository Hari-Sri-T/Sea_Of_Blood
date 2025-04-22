package object_java;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_sword extends SuperObject{
    GamePanel gp;
    public Obj_sword(GamePanel gp){
        this.gp=gp;
        name="Sword";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/object/Sword.png"));
            uTool.scaleImage(image, gp.tileSize,gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
