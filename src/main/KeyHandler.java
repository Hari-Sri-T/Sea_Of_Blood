package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyHandler implements KeyListener, MouseListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
        gp.addMouseListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        UI ui = gp.ui;
    
        if (gp.gameState == gp.loginState) {
    
            // TAB or ARROW DOWN: Switch to next field
            if (code == KeyEvent.VK_TAB || code == KeyEvent.VK_DOWN) {
                if (ui.enteringUsername) {
                    ui.enteringUsername = false;
                    ui.enteringPassword = true;
                } else {
                    ui.enteringUsername = true;
                    ui.enteringPassword = false;
                }
                e.consume();
    
            // ARROW UP: Switch to previous field
            } else if (code == KeyEvent.VK_UP) {
                if (ui.enteringPassword) {
                    ui.enteringPassword = false;
                    ui.enteringUsername = true;
                } else {
                    ui.enteringUsername = false;
                    ui.enteringPassword = true;
                }
                e.consume();
    
            // ENTER: Try login
            } else if (code == KeyEvent.VK_ENTER) {
                if (MySQLConnection.validateUser(ui.username, ui.password)) {
                    gp.gameState = gp.playState;
                    ui.showMessage("Login successful!");
                } else {
                    ui.showMessage("Invalid username or password");
                    if (!ui.rememberDetails) {
                        ui.username = "";
                        ui.password = "";
                    }
                    ui.enteringUsername = true;
                    ui.enteringPassword = false;
                }
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        UI ui = gp.ui;
    
        if (gp.gameState == gp.loginState) {
            if (c == '\b') {
                if (ui.enteringUsername && ui.username.length() > 0) {
                    ui.username = ui.username.substring(0, ui.username.length() - 1);
                } else if (ui.enteringPassword && ui.password.length() > 0) {
                    ui.password = ui.password.substring(0, ui.password.length() - 1);
                }
            } else if (Character.isDefined(c) && !Character.isISOControl(c)) {
                if (ui.enteringUsername) {
                    ui.username += c;
                } else if (ui.enteringPassword) {
                    ui.password += c;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) upPressed = false;
        if (code == KeyEvent.VK_S) downPressed = false;
        if (code == KeyEvent.VK_A) leftPressed = false;
        if (code == KeyEvent.VK_D) rightPressed = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        UI ui = gp.ui;
        int mouseX = e.getX();
        int mouseY = e.getY();
        int textHeight = 20;

        if (gp.gameState == gp.loginState) {
            int loginY = gp.tileSize * 2;

            if (mouseX >= gp.tileSize * 3 && mouseX <= gp.screenWidth - gp.tileSize * 3 &&
                mouseY >= loginY + gp.tileSize && mouseY <= loginY + gp.tileSize * 2) {
                ui.enteringUsername = true;
                ui.enteringPassword = false;
            } else if (mouseX >= gp.tileSize * 3 && mouseX <= gp.screenWidth - gp.tileSize * 3 &&
                       mouseY >= loginY + gp.tileSize * 3 && mouseY <= loginY + gp.tileSize * 4) {
                ui.enteringUsername = false;
                ui.enteringPassword = true;
            }

            String registerText = "No account? Register";
            int registerTextX = ui.getXforCentredText(registerText);
            int registerTextY = loginY + gp.tileSize * 7;
            int registerWidth = e.getComponent().getFontMetrics(ui.purisaB.deriveFont(20F)).stringWidth(registerText);

            if (mouseX >= registerTextX && mouseX <= registerTextX + registerWidth &&
                mouseY >= registerTextY - textHeight && mouseY <= registerTextY) {
                gp.gameState = gp.registerState;
                ui.regUsername = "";
                ui.regPassword = "";
                ui.regEnteringUsername = true;
                ui.regEnteringPassword = false;
                ui.showMessage("");
            }

            String forgotText = "Forgot Password?";
            int forgotTextX = ui.getXforCentredText(forgotText);
            int forgotTextY = loginY + gp.tileSize * 6;
            int forgotWidth = e.getComponent().getFontMetrics(ui.purisaB.deriveFont(20F)).stringWidth(forgotText);

            if (mouseX >= forgotTextX && mouseX <= forgotTextX + forgotWidth &&
                mouseY >= forgotTextY - textHeight && mouseY <= forgotTextY) {
                gp.gameState = gp.updatePasswordState;
                ui.updateUsername = "";
                ui.oldPassword = "";
                ui.newPassword = "";
                ui.confirmNewPassword = "";
                ui.enteringUpdateUsername = true;
                ui.enteringOldPassword = false;
                ui.enteringNewPassword = false;
                ui.enteringConfirmNewPassword = false;
                ui.updateMessage = "";
            }
        } else if (gp.gameState == gp.registerState) {
            int registerY = gp.tileSize * 2;

            if (mouseX >= gp.tileSize * 3 && mouseX <= gp.screenWidth - gp.tileSize * 3 &&
                mouseY >= registerY + gp.tileSize && mouseY <= registerY + gp.tileSize * 2) {
                ui.regEnteringUsername = true;
                ui.regEnteringPassword = false;
            } else if (mouseX >= gp.tileSize * 3 && mouseX <= gp.screenWidth - gp.tileSize * 3 &&
                       mouseY >= registerY + gp.tileSize * 3 && mouseY <= registerY + gp.tileSize * 4) {
                ui.regEnteringUsername = false;
                ui.regEnteringPassword = true;
            }

            String forgotText = "Forgot Password?";
            int forgotTextX = ui.getXforCentredText(forgotText);
            int forgotTextY = registerY + gp.tileSize * 6;
            int forgotWidth = e.getComponent().getFontMetrics(ui.purisaB.deriveFont(20F)).stringWidth(forgotText);

            if (mouseX >= forgotTextX && mouseX <= forgotTextX + forgotWidth &&
                mouseY >= forgotTextY - textHeight && mouseY <= forgotTextY) {
                gp.gameState = gp.updatePasswordState;
                ui.updateUsername = "";
                ui.oldPassword = "";
                ui.newPassword = "";
                ui.confirmNewPassword = "";
                ui.enteringUpdateUsername = true;
                ui.enteringOldPassword = false;
                ui.enteringNewPassword = false;
                ui.enteringConfirmNewPassword = false;
                ui.updateMessage = "";
            }

            String loginText = "Already have an account? Login";
            int loginTextX = ui.getXforCentredText(loginText);
            int loginTextY = registerY + gp.tileSize * 7;
            int loginWidth = e.getComponent().getFontMetrics(ui.purisaB.deriveFont(20F)).stringWidth(loginText);

            if (mouseX >= loginTextX && mouseX <= loginTextX + loginWidth &&
                mouseY >= loginTextY - textHeight && mouseY <= loginTextY) {
                gp.gameState = gp.loginState;
                ui.username = "";
                ui.password = "";
                ui.enteringUsername = true;
                ui.enteringPassword = false;
                ui.showMessage("");
            }
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
