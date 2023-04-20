package com;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Parent;

public class EndingController {
    
    @FXML
    public void switchToSecondary(int ending) throws IOException {

        Parent root;
        
        switch (ending){

            case 1:
            root = App.loadFXML("badEnding");
            break;
            case 2:
            root = App.loadFXML("goodEnding");
            break;

            default:
            root = null;
            break;
        }

        App.loadScene(root);

    }
}
