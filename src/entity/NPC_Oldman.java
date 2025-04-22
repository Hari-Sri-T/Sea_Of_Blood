package entity;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_Oldman extends Entity{


    public NPC_Oldman(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        getImage();;
        setDialogue();

    }
    public void getImage(){

        up1 = setup("/res/npc/oldman_up_1.png");
        up2 = setup("/res/npc/oldman_up_2.png");
        down1 = setup("/res/npc/oldman_down_1.png");
        down2 = setup("/res/npc/oldman_down_2.png");
        right1 = setup("/res/npc/oldman_right_1.png");
        right2 = setup("/res/npc/oldman_right_2.png");
        left1 = setup("/res/npc/oldman_left_1.png");
        left2 = setup("/res/npc/oldman_left_2.png");
    }
    public void setDialogue(){
        dialogues[0] = "Hello lad are u also\n stuck in this maze?";
        dialogues[1] = "To reach exit u should\n go up";
        dialogues[2] = "You want me to come with\n u ?? nahh im good here";
        dialogues[3] = "The outside world is filled\n with monsters its safer\n here";
    }
    public void setAction(){

        actionLockCounter++;
        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;
            if(i <= 25){
                direction = "up";

            }

            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;

        }

    }
    public void speak(){
        super.speak();
    }
}
