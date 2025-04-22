package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class Enemy extends Entity {

    public Enemy(GamePanel gp) {
        super(gp);
  
        direction = "down";
        speed = 1;

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        loadImages();
    }

    private void loadImages() {

        // Assign same sprites for all directions to keep logic compatible with Entity
        up1 = down1 = left1 = right1 = setup("/res/enemy/fly_1.png");
        up2 = down2 = left2 = right2 = setup("/res/enemy/fly_2.png");
    }

    @Override
    public void setAction() {
        // Chase player if close
        int dx = Math.abs(worldX - gp.player.worldX);
        int dy = Math.abs(worldY - gp.player.worldY);
        if (dx < gp.tileSize * 3 && dy < gp.tileSize * 3) {
            if (dx > dy) {
                direction = (worldX < gp.player.worldX) ? "right" : "left";
            } else {
                direction = (worldY < gp.player.worldY) ? "down" : "up";
            }
        } else {
            // Random wander
            actionLockCounter++;
            if (actionLockCounter >= 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;

                if (i <= 25) direction = "up";
                else if (i <= 50) direction = "down";
                else if (i <= 75) direction = "left";
                else direction = "right";

                actionLockCounter = 0;
            }
        }
    }

    @Override
    public void update() {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.enemy);
        gp.cChecker.checkEntity(this, new Entity[]{gp.player});

        if (!collisionOn) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }

        // Custom sprite change interval (10 instead of 12)
        spriteCounter++;
        if (spriteCounter > 3) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }
}
