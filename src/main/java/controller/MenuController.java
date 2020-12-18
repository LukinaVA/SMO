package controller;

import smo.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class MenuController {
    private App app;

    @FXML
    private TextField sNum;

    @FXML
    private TextField lambda;

    @FXML
    private TextField dNum;

    @FXML
    private TextField paramA;

    @FXML
    private TextField paramB;

    @FXML
    private TextField bSize;

    @FXML
    private TextField rNum;

    void init() {
        int sourceNum = Integer.parseInt(sNum.getText());
        double flowIntensity = Double.parseDouble(lambda.getText());
        int deviceNum = Integer.parseInt(dNum.getText());
        double a = Double.parseDouble(paramA.getText());
        double b = Double.parseDouble(paramB.getText());
        int bufferSize = Integer.parseInt(bSize.getText());
        int requestsNum = Integer.parseInt(rNum.getText());
        app.init(sourceNum, flowIntensity, deviceNum, a, b, bufferSize, requestsNum);
        app.runSimulation();
    }

    @FXML
    void startAutoMode(ActionEvent event) {
        init();
        app.openAutoMode();
    }

    @FXML
    void startStepMode(ActionEvent event) {
        init();
        app.openStepMode();
    }

    public void setApp(App app) {
        this.app = app;
    }
}
