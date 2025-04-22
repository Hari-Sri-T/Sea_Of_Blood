package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import java.io.InputStream;
import java.io.IOException;

import object_java.Obj_key;


public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font purisaB;
    Font maruMonica;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    // Login page fields
    String username = "";
    String password = "";
    boolean enteringUsername = true; // true: typing username, false: typing password
    boolean showPassword = false; // For simplicity, show password as asterisks
    boolean rememberDetails = false; // Checkbox for remembering details on login

    // Register page fields
    String regUsername = "";
    String regPassword = "";
    boolean regEnteringUsername = true;
    boolean regShowPassword = false;

    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;

    // Password update page fields
    String updateUsername = "";
    String oldPassword = "";
    String newPassword = "";
    String confirmNewPassword = "";
    boolean enteringUpdateUsername = true;
    boolean enteringOldPassword = false;
    boolean enteringNewPassword = false;
    boolean enteringConfirmNewPassword = false;
    boolean updateShowPassword = false;
    String updateMessage = "";
    boolean updateMessageOn = false;
    public boolean regEnteringPassword;
    public boolean enteringPassword;



    public UI(GamePanel gp){
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.out.println("Error loading fonts.");
        }


    }
    public void showMessage(String text){
        message = text;
        messageOn = true;

    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(purisaB.deriveFont(Font.PLAIN,35F));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);
<<<<<<< HEAD
        // LOGIN STATE
        if(gp.gameState == gp.loginState){
            drawLoginScreen();
        }
        // REGISTER STATE
        else if(gp.gameState == gp.registerState){
            drawRegisterScreen();
=======

        // TITLE STATE
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
>>>>>>> c89d38a1e95efda29e2a70ef49f6f7009a5ac5a0
        }
        // PLAY STATE
        else if(gp.gameState == gp.playState){

        }
        // PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            drawPauseScreeen();
        }
        // DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        // UPDATE PASSWORD STATE
        else if(gp.gameState == gp.updatePasswordState){
            drawUpdatePasswordScreen();
        }
    }
   

    public void drawTitleScreen() {
        BufferedImage titleBackground = null;
        try {
            titleBackground = ImageIO.read(getClass().getResourceAsStream("/res/title/Final Title Screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // Draw background
        if (titleBackground != null) {
            g2.drawImage(titleBackground, 0, 0, gp.screenWidth, gp.screenHeight, null);
        } else {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        }
    
       
        int x ;
        int y = gp.tileSize * 6;
       
    
        // Menu Options
        g2.setFont(maruMonica.deriveFont(Font.BOLD, 40F));
    
        String[] options = {"Play", "Exit"};
        y += gp.tileSize * 2;
    
        for (int i = 0; i < options.length; i++) {
            x = getXforCentredText(options[i]);
            if (i == commandNum) {
                g2.setColor(Color.RED);
            } else {
                g2.setColor(Color.WHITE);
            }
            g2.drawString(options[i], x, y + i * 50);
        }
    }
    
    
    public void drawPauseScreeen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        String text = "PAUSED";
        int x;
        int y;
        x = getXforCentredText(text);
        y = gp.screenHeight/2;

        g2.drawString(text,x,y);

    }
    public void drawDialogueScreen(){
        // Window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x,y,width,height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
        x += gp.tileSize;
        y += gp.tileSize;
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y += 40;
        }
    }
    public void drawLoginScreen(){
        // Draw login window
        int x = gp.tileSize * 3;
        int y = gp.tileSize * 2;
        int width = gp.screenWidth - gp.tileSize * 6;
        int height = gp.tileSize * 9; // Reduced height to better fit elements and show links
        drawSubWindow(x, y, width, height);

        g2.setFont(new Font("Arial", Font.BOLD, 40));
        String title = "Login";
        int titleX = getXforCentredText(title);
        int titleY = y + gp.tileSize;
        g2.drawString(title, titleX, titleY);

        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        int labelX = x + gp.tileSize;
        int inputX = labelX + 150;
        int usernameY = titleY + gp.tileSize * 2;
        int passwordY = usernameY + gp.tileSize * 2;

        g2.drawString("Username:", labelX, usernameY);
        g2.drawString("Password:", labelX, passwordY);

        // Draw username input
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(inputX, usernameY - 30, 200, 40);
        g2.setColor(Color.BLACK);
        g2.drawRect(inputX, usernameY - 30, 200, 40);
        g2.setColor(Color.BLACK);
        g2.drawString(username, inputX + 5, usernameY);

        // Draw password input (show asterisks)
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(inputX, passwordY - 30, 200, 40);
        g2.setColor(Color.BLACK);
        g2.drawRect(inputX, passwordY - 30, 200, 40);
        String displayedPassword = showPassword ? password : "*".repeat(password.length());
        g2.setColor(Color.BLACK);
        g2.drawString(displayedPassword, inputX + 5, passwordY);
        // Removed alt text or placeholder for password field

        // Draw "Remember the details" checkbox
        int checkboxX = inputX;
        int checkboxY = passwordY + gp.tileSize;
        int checkboxSize = 20;
        g2.setColor(Color.WHITE);
        g2.drawRect(checkboxX, checkboxY, checkboxSize, checkboxSize);
        if(rememberDetails){
            g2.drawLine(checkboxX, checkboxY, checkboxX + checkboxSize, checkboxY + checkboxSize);
            g2.drawLine(checkboxX, checkboxY + checkboxSize, checkboxX + checkboxSize, checkboxY);
        }
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.drawString("Remember the details", checkboxX + checkboxSize + 10, checkboxY + checkboxSize - 5);

        // Draw instruction
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        String instruction = "Press TAB to switch, ENTER to submit";
        int instructionX = getXforCentredText(instruction);
        int instructionY = y + height - gp.tileSize * 3;
        g2.setColor(Color.WHITE);
        g2.drawString(instruction, instructionX, instructionY);

        // Draw clickable register text
        String registerText = "No account? Register";
        int registerX = getXforCentredText(registerText);
        int registerY = instructionY + gp.tileSize * 2;
        g2.setColor(Color.CYAN);
        g2.drawString(registerText, registerX, registerY);

        // Draw clickable "Forgot password?" link
        String forgotText = "Forgot password?";
        int forgotX = getXforCentredText(forgotText);
        int forgotY = registerY + gp.tileSize * 2;
        g2.setColor(Color.CYAN);
        g2.drawString(forgotText, forgotX, forgotY);

        // Draw cursor for active input
        int cursorX;
        int cursorY;
        if(enteringUsername){
            cursorX = inputX + 5 + g2.getFontMetrics().stringWidth(username);
            cursorY = usernameY;
        } else {
            cursorX = inputX + 5 + g2.getFontMetrics().stringWidth(displayedPassword);
            cursorY = passwordY;
        }
        g2.setColor(Color.WHITE);
        g2.drawLine(cursorX, cursorY - 25, cursorX, cursorY - 5);
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height,35 , 35);
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    public int getXforCentredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2-length/2;
        return  x;
    }

    public void drawRegisterScreen(){
        // Draw register window
        int x = gp.tileSize * 3;
        int y = gp.tileSize * 2;
        int width = gp.screenWidth - gp.tileSize * 6;
        int height = gp.tileSize * 8;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        String title = "Register";
        int titleX = getXforCentredText(title);
        int titleY = y + gp.tileSize;
        g2.drawString(title, titleX, titleY);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        int labelX = x + gp.tileSize;
        int inputX = labelX + 150;
        int usernameY = titleY + gp.tileSize * 2;
        int passwordY = usernameY + gp.tileSize * 2;

        g2.drawString("Username:", labelX, usernameY);
        g2.drawString("Password:", labelX, passwordY);

        // Draw username input
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(inputX, usernameY - 30, 200, 40);
        g2.setColor(Color.BLACK);
        g2.drawRect(inputX, usernameY - 30, 200, 40);
        g2.drawString(regUsername, inputX + 5, usernameY);

        // Draw password input (show asterisks)
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(inputX, passwordY - 30, 200, 40);
        g2.setColor(Color.BLACK);
        g2.drawRect(inputX, passwordY - 30, 200, 40);
        String displayedPassword = regShowPassword ? regPassword : "*".repeat(regPassword.length());
        g2.drawString(displayedPassword, inputX + 5, passwordY);
        // Removed alt text or placeholder for password field

        // Draw instruction
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        String instruction = "Press TAB to switch, ENTER to submit";
        int instructionX = getXforCentredText(instruction);
        int instructionY = y + height - gp.tileSize * 2;
        g2.drawString(instruction, instructionX, instructionY);

        // Draw clickable login text
        String loginText = "Already have an account? Login";
        int loginX = getXforCentredText(loginText);
        int loginY = instructionY + gp.tileSize * 2;
        g2.setColor(Color.CYAN);
        g2.drawString(loginText, loginX, loginY);
        // Removed alt text or underline for clickable text
        // No additional visual effect added here

        // Draw cursor for active input
        int cursorX;
        int cursorY;
        if(regEnteringUsername){
            cursorX = inputX + 5 + g2.getFontMetrics().stringWidth(regUsername);
            cursorY = usernameY;
        } else {
            cursorX = inputX + 5 + g2.getFontMetrics().stringWidth(displayedPassword);
            cursorY = passwordY;
        }
        g2.drawLine(cursorX, cursorY - 25, cursorX, cursorY - 5);
    }

    public void drawUpdatePasswordScreen(){
        // Draw update password window
        int x = gp.tileSize * 3;
        int y = gp.tileSize * 2;
        int width = gp.screenWidth - gp.tileSize * 6;
        int height = gp.tileSize * 14; // Increased height to fit more elements
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        String title = "Update Password";
        int titleX = getXforCentredText(title);
        int titleY = y + gp.tileSize;
        g2.drawString(title, titleX, titleY);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        int labelX = x + gp.tileSize;
        int inputX = labelX + 250;
        int usernameY = titleY + gp.tileSize * 2;
        int oldPasswordY = usernameY + gp.tileSize * 2;
        int newPasswordY = oldPasswordY + gp.tileSize * 2;
        int confirmPasswordY = newPasswordY + gp.tileSize * 2;

        g2.drawString("Username:", labelX, usernameY);
        g2.drawString("Old Password:", labelX, oldPasswordY);
        g2.drawString("New Password:", labelX, newPasswordY);
        g2.drawString("Confirm New Password:", labelX, confirmPasswordY);

        // Draw username input
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(inputX, usernameY - 30, 200, 40);
        g2.setColor(Color.BLACK);
        g2.drawRect(inputX, usernameY - 30, 200, 40);
        g2.setColor(Color.BLACK);
        g2.drawString(updateUsername, inputX + 5, usernameY);
        // Draw old password input
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(inputX, oldPasswordY - 30, 200, 40);
        g2.setColor(Color.BLACK);
        g2.drawRect(inputX, oldPasswordY - 30, 200, 40);
        String displayedOldPassword = updateShowPassword ? oldPassword : "*".repeat(oldPassword.length());
        g2.setColor(Color.BLACK);
        g2.drawString(displayedOldPassword, inputX + 5, oldPasswordY);

        // Draw new password input
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(inputX, newPasswordY - 30, 200, 40);
        g2.setColor(Color.BLACK);
        g2.drawRect(inputX, newPasswordY - 30, 200, 40);
        String displayedNewPassword = updateShowPassword ? newPassword : "*".repeat(newPassword.length());
        g2.setColor(Color.BLACK);
        g2.drawString(displayedNewPassword, inputX + 5, newPasswordY);

        // Draw confirm new password input
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(inputX, confirmPasswordY - 30, 200, 40);
        g2.setColor(Color.BLACK);
        g2.drawRect(inputX, confirmPasswordY - 30, 200, 40);
        String displayedConfirmPassword = updateShowPassword ? confirmNewPassword : "*".repeat(confirmNewPassword.length());
        g2.setColor(Color.BLACK);
        g2.drawString(displayedConfirmPassword, inputX + 5, confirmPasswordY);

        // Draw instruction
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        String instruction = "Press TAB to switch fields, ENTER to submit, ESC to cancel";
        int instructionX = getXforCentredText(instruction);
        int instructionY = y + height - gp.tileSize * 2;
        g2.setColor(Color.WHITE);
        g2.drawString(instruction, instructionX, instructionY);

        // Draw update message if any
        if(updateMessageOn){
            g2.setColor(Color.RED);
            int messageX = getXforCentredText(updateMessage);
            int messageY = instructionY - gp.tileSize;
            g2.drawString(updateMessage, messageX, messageY);
        }
    }
}