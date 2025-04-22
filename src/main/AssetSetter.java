package main;
import entity.Enemy;
import entity.NPC_Oldman;
import object_java.Obj_chest;
import object_java.Obj_door;
import object_java.Obj_key;
import object_java.Obj_sword;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new Obj_sword(gp);
        gp.obj[0].worldX = gp.tileSize * 12; // example coordinates
        gp.obj[0].worldY = gp.tileSize * 5;


    }
    public void setNPC(){
        gp.npc[0] = new NPC_Oldman(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;


    }
    public void setEnemy() {
        // Example of enemy initialization, set positions accordingly
        gp.enemy[0] = new Enemy(gp);
        gp.enemy[0].worldX = 23 * gp.tileSize;
        gp.enemy[0].worldY = 21 * gp.tileSize;
    
        gp.enemy[1] = new Enemy(gp);
        gp.enemy[1].worldX = 30 * gp.tileSize;
        gp.enemy[1].worldY = 28 * gp.tileSize;
    
        gp.enemy[2] = new Enemy(gp);
        gp.enemy[2].worldX = 35 * gp.tileSize;
        gp.enemy[2].worldY = 20 * gp.tileSize;

        gp.enemy[3] = new Enemy(gp);
        gp.enemy[3].worldX = 32 * gp.tileSize;
        gp.enemy[3].worldY = 34 * gp.tileSize;

        gp.enemy[4] = new Enemy(gp);
        gp.enemy[4].worldX = 39 * gp.tileSize;
        gp.enemy[4].worldY = 28 * gp.tileSize;
        // More enemies can be added here
    }
    


}
