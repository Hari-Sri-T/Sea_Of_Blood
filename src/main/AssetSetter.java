package main;
import object.Obj_chest;
import object.Obj_door;
import object.Obj_key;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new Obj_key();
        gp.obj[0].worldX = 9 * gp.tileSize;
        gp.obj[0].worldY = 9 * gp.tileSize;

        gp.obj[1] = new Obj_door();
        gp.obj[1].worldX = 11 * gp.tileSize;
        gp.obj[1].worldY = 11 * gp.tileSize;

        gp.obj[2] = new Obj_chest();
        gp.obj[2].worldX = 12 * gp.tileSize;
        gp.obj[2].worldY = 12 * gp.tileSize;

        gp.obj[3] = new Obj_chest();
        gp.obj[3].worldX = 15 * gp.tileSize;
        gp.obj[3].worldY = 15 * gp.tileSize;




    }
}
