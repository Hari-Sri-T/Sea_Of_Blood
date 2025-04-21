package main;
import entity.NPC_Oldman;
import object.Obj_chest;
import object.Obj_door;
import object.Obj_key;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

    }
    public void setNPC(){
        gp.npc[0] = new NPC_Oldman(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;


    }
}
