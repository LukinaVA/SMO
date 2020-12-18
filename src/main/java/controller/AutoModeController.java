package controller;

import smo.App;
import javafx.fxml.FXML;
import smo.Statistics;
import smo.DeviceResult;
import smo.SourceResult;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class AutoModeController {
    private App app;

    @FXML
    private TableView<SourceResult> sources;

    @FXML
    private TableView<DeviceResult> devices;

    @FXML
    private TableColumn<?, ?> sNum;

    @FXML
    private TableColumn<?, ?> genNum;

    @FXML
    private TableColumn<?, ?> rejectP;

    @FXML
    private TableColumn<?, ?> timeIn;

    @FXML
    private TableColumn<?, ?> waitTime;

    @FXML
    private TableColumn<?, ?> handleTime;

    @FXML
    private TableColumn<?, ?> devNum;

    @FXML
    private TableColumn<?, ?> coef;

    @FXML
    void initialize() {
        sNum.setCellValueFactory(new PropertyValueFactory<>("sourceNum"));
        genNum.setCellValueFactory(new PropertyValueFactory<>("requestNum"));
        rejectP.setCellValueFactory(new PropertyValueFactory<>("failureProbability"));
        timeIn.setCellValueFactory(new PropertyValueFactory<>("averageTimeInSystem"));
        waitTime.setCellValueFactory(new PropertyValueFactory<>("averageWaitTime"));
        handleTime.setCellValueFactory(new PropertyValueFactory<>("averageHandleTime"));

        devNum.setCellValueFactory(new PropertyValueFactory<>("deviceNum"));
        coef.setCellValueFactory(new PropertyValueFactory<>("utilization"));
    }

    public void printInfo(Statistics statistics) {
        ObservableList<SourceResult> sourceResults = FXCollections.observableArrayList();
        sourceResults.addAll(statistics.getSourceResultList());
        this.sources.setItems(sourceResults);

        ObservableList<DeviceResult> deviceResults = FXCollections.observableArrayList();
        deviceResults.addAll(statistics.getDeviceResultList());
        this.devices.setItems(deviceResults);
    }

    @FXML
    void openMenu() {
        sources.refresh();
        devices.refresh();
        app.openMenu();
    }

    public void setApp(App app) {
        this.app = app;
    }
}
