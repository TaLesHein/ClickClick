package com;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;

public class ClickClickController {

    // Tela para a mensagem do Comprador, com os botões de resposta imbutido
    @FXML
    private AnchorPane anchorMsg;

    // Botão de Não para o Comprador
    @FXML
    private Button btnNo;

    // Botão de Sim para o Comprador
    @FXML
    private Button btnYes;

    // Botão de Ok após recusar oferta do Comprador
    // E também o botão de receber 1 moeda para o milhão
    @FXML
    private Button btnOk;

    // Botão de ... após o Comprador ir embora por terem recusado sua oferta
    @FXML
    private Button btnPoints;

    // Mensagem do Comprador
    @FXML
    private Label speechBubble;

    // Placa de Vendido
    @FXML
    private Label plateSold;

    // Botão Principal
    @FXML
    private Button btnMain;

    // Botão para Vencer : 100000
    @FXML
    private Button btnWin;

    // Botão Upgrade 1 : Clique ++
    @FXML
    private Button upgradeButton1;

    // Botão Upgrade 2 : Clique #
    @FXML
    private Button upgradeButton2;

    // Botão Upgrade 3 : Clique por um Desconto
    @FXML
    private Button upgradeButton3;

    // Upgrade 1 : Descrição
    @FXML
    private Label descUpgrade1;

    // Upgrade 2 : Descrição
    @FXML
    private Label descUpgrade2;

    // Upgrade 3 : Descrição
    @FXML
    private Label descUpgrade3;

    // Upgrade 1 : Valor Total
    @FXML
    private Label qtnUpgrade1;

    // Upgrade 2 : Valor Total
    @FXML
    private Label qtnUpgrade2;

    // Upgrade 3 : Valor Total
    @FXML
    private Label qtnUpgrade3;

    // Click Coins : Valor Total
    @FXML
    private Label labelClickCoins;

    // Total de Clicks : Valor Total
    @FXML
    private Label labelTotClicks;

    // Variáveis dos Cliques
    int clickCoins, totCoins, totClicks;

    // Modo de jogo
    int mode;

    // Quantidade de cada upgrade
    static int clique = 1, mult = 1;
    static float desconto = 0;

    // Valor de cada upgrade
    int valorUpgrade1 = 10, valorUpgrade2 = 100, valorUpgrade3 = 1000;

    // Aparição do comprador
    boolean buyerAppeared = false;

    // Controle do botão Ok
    boolean btnNewValue = false;

    // Controle do final
    int endingValue;

    // Objeto da classe Borders
    Borders border = new Borders();

    // Método de inicialização do jogo
    @FXML
    void initialize() {

        // Definindo o texto de cada upgrade
        String txtUpgrade1 = Integer.toString(valorUpgrade1);
        TxtButtons(txtUpgrade1, 1);

        String txtUpgrade2 = Integer.toString(valorUpgrade2);
        TxtButtons(txtUpgrade2, 2);

        String txtUpgrade3 = Integer.toString(valorUpgrade3);
        TxtButtons(txtUpgrade3, 3);
    }

    // Botão Principal
    @FXML
    void BtnMainClick(ActionEvent event) {

        // clickCoins
        clickCoins += clique * mult;

        // Limitador para as moedas
        if (clickCoins >= mode) {
            clickCoins = mode - 1;

            // Se o comprador não apareceu, ele aparece;
            // Agora se já apareceu, não aparece mais.
            if (buyerAppeared == false) {
                buyerAppeared = true;
                anchorMsg.setVisible(true);
            }

        }

        // Passando o valor das moedas para o label
        labelClickCoins.setText(Integer.toString(clickCoins));

        // Total de Cliques
        totClicks++;
        labelTotClicks.setText(Integer.toString(totClicks));

        // Chamando o método para checar se os upgrades podem ser compráveis
        checkUpgrades();
    }

    // Botão para Vencer : Click Coins 1000000
    @FXML
    void btnWinClick(ActionEvent event) throws IOException {

        // Se o total de moedas for diferente das definidas pelo mode, retornar
        if (clickCoins != mode) {
            return;
        }

        // Criando objeto da classe EndingController para acessar o final do jogo
        EndingController end = new EndingController();
        end.switchToSecondary(endingValue);

    }

    // Upgrade 1 : Clique ++
    @FXML
    void BtnUp1(ActionEvent event) {
        // Se o valor das moedas forem maior ou igual ao valor, comprar
        if (clickCoins >= valorUpgrade1) {

            // Adicionando o upgrade de aumento de clique ao player
            clique++;

            // Atualizando a quantidade do upgrade
            qtnUpgrade1.setText(Integer.toString(clique));

            // Comprar Upgrade
            Bag(valorUpgrade1);

            // Novo preço e definir texto do botão
            valorUpgrade1 = NewPrice(valorUpgrade1);

            // Transformar valor em texto para adicionar no botão
            String txtUpgrade = Integer.toString(valorUpgrade1);
            TxtButtons(txtUpgrade, 1);
        }
    }

    // Upgrade 2 : Clique #
    @FXML
    void BtnUp2(ActionEvent event) {
        if (clickCoins >= valorUpgrade2) {

            // Adicionando o upgrade de aumento de multiplicador ao player
            mult++;

            // Atualizando a quantidade do upgrade
            qtnUpgrade2.setText(Integer.toString(mult));

            // Comprar Upgrade
            Bag(valorUpgrade2);

            // Novo preço e definir texto do botão
            valorUpgrade2 = NewPrice(valorUpgrade2);

            // Transformar valor em texto para adicionar no botão
            String txtUpgrade = Integer.toString(valorUpgrade2);
            TxtButtons(txtUpgrade, 2);
        }
    }

    // Upgrade 3 : Clique por um Desconto
    @FXML
    void BtnUp3(ActionEvent event) {

        if (desconto == 35) {
            upgradeButton3.setDisable(true);
            upgradeButton3.setBorder(new Border(border.strokes[2]));

        }

        if (clickCoins >= valorUpgrade3) {
            // Aumento do desconto
            desconto += 5;
            qtnUpgrade3.setText(Float.toString(desconto));

            // Comprar Upgrade
            Bag(valorUpgrade3);

            // Novo preço
            valorUpgrade3 = NewPrice(valorUpgrade3);

            // Transformar valor em texto para adicionar no botão
            String txtUpgrade3 = Integer.toString(valorUpgrade3);
            TxtButtons(txtUpgrade3, 3);

            // Descontando o preço dos outros upgrades
            valorUpgrade1 = Discounting(valorUpgrade1);
            valorUpgrade2 = Discounting(valorUpgrade2);

            for (int i = 1; i <= 2; i++) {

                String txtUpgrade;

                switch (i) {

                    case 1:
                        txtUpgrade = Integer.toString(valorUpgrade1);
                        TxtButtons(txtUpgrade, i);
                        break;

                    case 2:
                        txtUpgrade = Integer.toString(valorUpgrade2);
                        TxtButtons(txtUpgrade, i);
                        break;
                }
            }

        }
    }

    // Botão de resposta ao Comprador (Yes)
    @FXML
    void btnYesClick(ActionEvent event) {

        // Definindo o final ruim
        endingValue = 1;
        speechBubble.setText("Ótima escolha, garanto que não se arrependerá. Até mais ver, caro amigo.");

        // Deixando botões de respostas invisíveis
        btnYes.setVisible(false);
        btnNo.setVisible(false);

        // Trocando o botão principal pela placa de Vendido
        plateSold.setVisible(true);

        // Chamando método para desabilitar os botões e receber +1 moeda
        HitGoal();
    }

    // Botão de resposta ao Comprador (No)
    @FXML
    void btnNoClick(ActionEvent event) {

        // Definindo o final bom
        endingValue = 2;

        // Alterar o balão de fala do Comprador
        speechBubble.setText("Vai se arrepender desta escolha caro amigo");

        // Deixar botões Yes e No invisíveis
        btnYes.setVisible(false);
        btnNo.setVisible(false);

        // Deixar o botão Ok visível
        btnOk.setVisible(true);
    }

    // Botão de resposta ao Comprador, além de ser o botão da última moeda
    @FXML
    void btnOkClick(ActionEvent event) {

        // Se o texto do botão Ok estiver como "Moeda", adicione uma moeda
        if (btnOk.getText().equalsIgnoreCase("Moeda")) {

            // Chamando método para desabilitar os botões e receber +1 moeda
            HitGoal();
        }

        // Se não realize estás ações
        // Deixar o balão de fala e o botão Ok invisíveis
        speechBubble.setVisible(false);
        btnOk.setVisible(false);

        // Deixar o botão Points visível
        if (!btnOk.getText().equalsIgnoreCase("Moeda")) {
            btnPoints.setVisible(true);
        }

    }

    // Botão de ação após o Comprador ir embora
    @FXML
    void btnPointsClick(ActionEvent event) {

        // Definindo o botão como invisível
        btnPoints.setVisible(false);

        // Mudando o balão de fala e deixando-o visível
        speechBubble.setText(
                "O idiota deixou cair sua carteira, e que por muita coincidência contém todas as moedas que lhe faltam. Que sorte!!");
        speechBubble.setVisible(true);

        // Trocando o design do botão para "Moeda"
        btnOk.setText("Moeda");
        btnOk.setStyle("-fx-text-fill: #cd9507;");
        btnOk.setBorder(new Border(border.strokes[5]));
        btnOk.setVisible(true);
    }

    // Checando se o player pode comprar os upgrades
    void checkUpgrades() {

        for (int i = 1; i <= 3; i++) {

            switch (i) {

                case 1:
                    if (clickCoins >= valorUpgrade1) {
                        changeBtnColor(upgradeButton1, true);
                    }
                    else{
                        changeBtnColor(upgradeButton1, false);
                    }
                    break;

                case 2:
                    if (clickCoins >= valorUpgrade2) {
                        changeBtnColor(upgradeButton2, true);
                    }
                    else{
                        changeBtnColor(upgradeButton2, false);
                    }
                    break;

                case 3:
                    if (desconto == 40) {
                        break;
                    } else if (clickCoins >= valorUpgrade3) {
                        changeBtnColor(upgradeButton3, true);
                    }
                    else{
                        changeBtnColor(upgradeButton3, false);
                    }
                    break;
            }
        }
    }

    // Alterar a cor do botão
    void changeBtnColor(Button bnt, boolean canBuy) {

        if (canBuy){
            bnt.setBorder(new Border(border.strokes[4]));
        }
        else{
            bnt.setBorder(new Border(border.strokes[0]));
        }
        
    }

    @FXML
    void mouseWEnter(MouseEvent event) {
        if (clickCoins == mode) {
            btnWin.setBorder(new Border(Borders.strokes[4]));
        } else {
            btnWin.setBorder(new Border(Borders.strokes[2]));
            btnWin.setStyle("-fx-text-fill: #EB1C01;");
        }
    }

    @FXML
    void mouseWExited(MouseEvent event) {
        btnWin.setBorder(new Border(Borders.strokes[0]));
        if (clickCoins != mode) {
            btnWin.setStyle("-fx-text-fill: BLACK;");
        }

    }

    // Compra de upgrade
    void Bag(int price) {

        // Subtraindo o preço da compra
        clickCoins -= price;
        labelClickCoins.setText(Integer.toString(clickCoins));

        checkUpgrades();
    }

    // Definindo novo preço
    int NewPrice(int price) {

        // Preço vezes 2
        price *= 2;

        // Chamando o método para discontar do preço atual
        price = Discounting(price);

        return price;
    }

    // Descontando o preço
    int Discounting(int price) {

        float porcent = (desconto * price) / 100;
        price -= porcent;

        return price;

    }

    // Desabilitando os botões após alcançar o objetivo
    void HitGoal() {

        // Deixando o texto do btnWin como Verde
        btnWin.setStyle("-fx-text-fill: GREEN;");

        // Deixando botões de upgrades e o main desabilitados
        btnMain.setDisable(true);
        upgradeButton1.setDisable(true);
        upgradeButton2.setDisable(true);
        upgradeButton3.setDisable(true);

        // Definindo as moedas com o valor do objetivo
        // Evitando com que o player gaste-as e não
        // consiga zerar o jogo.
        clickCoins = mode;

        labelClickCoins.setText(Integer.toString(clickCoins));
    }

    // Método para definir o texto dos botões
    void TxtButtons(String txt, int numberBtn) {

        // Definindo o texto de cada botão
        switch (numberBtn) {
            case 1:
                upgradeButton1.setText(txt + " ClickCoins");
                return;
            case 2:
                upgradeButton2.setText(txt + " ClickCoins");
                return;
            case 3:
                if (desconto == 40) {
                    upgradeButton3.setText("Sold off");
                } else {
                    upgradeButton3.setText(txt + " ClickCoins");
                }
                return;
        }
    }

    // Configuração do modo Simples
    void simpleModeConfig() {

        // Desativando o upgrade 3
        upgradeButton3.setDisable(true);

        // Alterando texto do botão com o valor de 1000 cliques
        btnWin.setText("Alcance 1000 cliques para vencer");
    }

    // Definindo o modo do jogo
    public void setMode(boolean mode) {

        // Criando um objeto da classe ModeSetting
        ModeSetting modeSetting = new ModeSetting();

        // Chamando método setMode e passando o parâmetro mode para ele
        modeSetting.setMode(mode);
        this.mode = modeSetting.getMode();

        // Se o modo de jogo for o Simples, chamar o método simpleModeConfing
        if (mode == false) {
            simpleModeConfig();
        }

    }

}