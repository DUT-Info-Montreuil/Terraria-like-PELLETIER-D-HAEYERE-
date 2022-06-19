package Terraria.modele;

import javafx.scene.layout.Pane;

public class KeyHandler {
    private boolean rightPressed, leftPressed, upPressed, downPressed;
    private boolean inventoryTyped;
    private boolean interactionTyped;



    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isInteractionTyped() {
        return interactionTyped;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isInventoryTyped() {
        return inventoryTyped;
    }

    private final Pane pane;


    public KeyHandler(Pane pane) {
        rightPressed = false;
        leftPressed = false;
        upPressed = false;
        downPressed = false;
        inventoryTyped=false;
        interactionTyped=false;

        this.pane = pane;
    }




    private void keyPressed() {
        pane.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case D:
                    rightPressed = true;
                    break;
                case Q:
                    leftPressed = true;
                    break;
                case S:
                    downPressed = true;
                    break;
                case SPACE:

                    upPressed = true;
                    break;
                case TAB:
                    inventoryTyped = !this.inventoryTyped;

                    break;
                case E:
                    interactionTyped= !this.interactionTyped;
            }
        });
    }

    private void keyReleased() {
        pane.setOnKeyReleased(e -> {

            switch (e.getCode()) {
                case D:
                    rightPressed = false;
                    break;
                case Q:
                    leftPressed = false;
                    break;
                case S:
                    downPressed = false;
                    break;
                case SPACE:

                    upPressed = false;
                    break;

            }
        });
    }



    public void start() {
        this.keyPressed();
        this.keyReleased();


    }


}