package com;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class ModeController {

    @FXML
    private Button btnNormal;

    @FXML
    private Button btnSimple;

    // Objeto da classe Borders
    Borders borders = new Borders();


    @FXML
    void initialize(){

    // Simple Mode
        Text normalS = new Text();
        Text italicS = new Text();

        // Definindo a primeira parte do texto "Simple Mode"
        normalS.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 23));
        normalS.setFill(Color.BLUE);
        normalS.setStroke(Color.web("#323232"));
        normalS.setStrokeWidth(0.5);
        normalS.setText("Simple Mode");

        // Definindo a segunda parte do texto "(0 a 1 min)"
        italicS.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 21));
        italicS.setFill(Color.web("#323232"));
        italicS.setText(" (0 a 1 min)");

        // Juntando ambos os textos em um só, além de alinha-los
        TextFlow txtFlow = new TextFlow(normalS, italicS);
        txtFlow.setTextAlignment(TextAlignment.CENTER);

        // Passando o texto para o botão
        btnSimple.setGraphic(txtFlow);


    // Normal Mode
        Text normalN = new Text();
        Text italicN = new Text();

        // Definindo a primeira parte do texto "Normal Mode"
        normalN.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 23));
        normalN.setFill(Color.RED);
        normalN.setStroke(Color.web("#323232"));
        normalN.setStrokeWidth(0.5);
        normalN.setText("Normal Mode");

        // Definindo a segunda parte do texto "(5 a 10 min)"
        italicN.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 21));
        italicN.setFill(Color.web("#323232"));
        italicN.setText(" (5 a 10 min)");

        // Juntando ambos os textos em um só, além de alinha-los
        txtFlow = new TextFlow(normalN, italicN);
        txtFlow.setTextAlignment(TextAlignment.CENTER);

        // Passando o texto para o botão
        btnNormal.setGraphic(txtFlow);


    }

    // Modo de jogo Normal
    @FXML
    void btnNormalClick(ActionEvent event) throws IOException {

        // Iniciando o FXML, assim conseguindo controla-lo
        FXMLLoader loader = new FXMLLoader(getClass().getResource("clickClick.fxml"));
        Parent root = App.loadFXML(loader);

        // Definindo o modo de jogo como Normal
        ClickClickController clickController = loader.getController();
        clickController.setMode(true);

        // Carregando a cena
        App.loadScene(root);

    }

    // Modo de jogo Simples
    @FXML
    void btnSimpleClick(ActionEvent event) throws IOException {
        
        // Iniciando o FXML, assim conseguindo controla-lo
        FXMLLoader loader = new FXMLLoader(getClass().getResource("clickClick.fxml"));
        Parent root = App.loadFXML(loader);

        // Definindo o modo de jogo como Simples
        ClickClickController clickController = loader.getController();
        clickController.setMode(false);

        // Carregando a cena
        App.loadScene(root);
    }

    @FXML
    void mouseNEnter(MouseEvent event) {
        // Definindo a cor da borda do btnNormal como azul ao tocar
        btnNormal.setBorder(new Border(borders.strokes[1]));
    }

    @FXML
    void mouseNExit(MouseEvent event) {
        // Definindo a borda como default ao sair
        btnNormal.setBorder(new Border(borders.strokes[0]));
    }

    @FXML
    void mouseSEnter(MouseEvent event) {
        // Definindo a cor da borda do btnSimple como vermelho ao tocar
        btnSimple.setBorder(new Border (borders.strokes[3]));
    }

    @FXML
    void mouseSExit(MouseEvent event) {
        // Definindo a borda como default ao sair
        btnSimple.setBorder(new Border(borders.strokes[0]));
    }

}
