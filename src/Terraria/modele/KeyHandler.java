package Terraria.modele;

import javafx.scene.layout.Pane;
import javafx.scene.*;
import javafx.scene.input.*;

public class KeyHandler {
    private boolean rightPressed, leftPressed, upPressed, downPressed;
    private boolean slotTwoTyped;
    private boolean slotThreeTyped;
    private boolean slotFourTyped;
    private boolean slotFiveTyped;
    private boolean slotSixTyped;
    private boolean slotSevenTyped;
    private boolean slotEightTyped;
    private boolean slotNineTyped;
    private boolean slotTenTyped;



    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    private Pane pane;


    public KeyHandler(Pane pane) {
        rightPressed = false;
        leftPressed = false;
        upPressed = false;
        downPressed = false;
        slotTenTyped = false;
        slotNineTyped = false;
        slotEightTyped = false;
        slotSevenTyped = false;
        slotSixTyped = false;
        slotFiveTyped = false;
        slotFourTyped = false;
        slotThreeTyped = false;
        slotTwoTyped = false;
        boolean slotOneTyped = false;
        this.pane = pane;
    }




    private void keyPressed() {
        pane.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case D :
                    rightPressed = true;
                    break;
                case Q :
                    leftPressed = true;
                    break;
                case S :
                    downPressed = true;
                    break;
                case SPACE:
                    upPressed = true;
                    break;
            }
        });
    }

    private void keyReleased() {
        pane.setOnKeyReleased(e -> {
            //System.out.println(e.getCode());
            switch (e.getCode()) {
                case D :
                    rightPressed = false;
                    break;
                case Q :
                    leftPressed = false;
                    break;
                case S :
                    downPressed = false;
                    break;
                case SPACE:
                    upPressed = false;
                    break;
            }
        });
    }

    public void setPanel(Pane pane) {
        this.pane = pane;
    }

    public void start() {
        this.keyPressed();
        this.keyReleased();


    }


}