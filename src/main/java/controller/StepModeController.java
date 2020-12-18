package controller;

import smo.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class StepModeController {
    private App app;

    private int step;
    private List<String> eventCalendar;
    private List<List<SourceResult>> sourcesStates;
    private List<List<BufferResult>> bufferStates;
    private List<List<DeviceResult>> devicesStates;

    @FXML
    private TextArea events;

    @FXML
    private TableView<SourceResult> sources;

    @FXML
    private TableView<DeviceResult> devices;

    @FXML
    private TableView<BufferResult> buffer;

    @FXML
    private TableColumn<?, ?> sourceNum;

    @FXML
    private TableColumn<?, ?> genTime;

    @FXML
    private TableColumn<?, ?> reqNum;

    @FXML
    private TableColumn<?, ?> rejReqNum;

    @FXML
    private TableColumn<?, ?> position;

    @FXML
    private TableColumn<?, ?> bufGenTime;

    @FXML
    private TableColumn<?, ?> bufSourceNum;

    @FXML
    private TableColumn<?, ?> bufRequestNum;

    @FXML
    private TableColumn<?, ?> deviceNum;

    @FXML
    private TableColumn<?, ?> releaseTime;

    @FXML
    private TableColumn<?, ?> downtime;

    @FXML
    private TableColumn<?, ?> sNum;

    @FXML
    private TableColumn<?, ?> rNum;

    @FXML
    private TableColumn<?, ?> gTime;

    @FXML
    void initialize() {
        sourceNum.setCellValueFactory(new PropertyValueFactory<>("sourceNum"));
        genTime.setCellValueFactory(new PropertyValueFactory<>("genTime"));
        reqNum.setCellValueFactory(new PropertyValueFactory<>("reqNum"));
        rejReqNum.setCellValueFactory(new PropertyValueFactory<>("rejReqNum"));

        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        bufGenTime.setCellValueFactory(new PropertyValueFactory<>("genTime"));
        bufSourceNum.setCellValueFactory(new PropertyValueFactory<>("sourceNum"));
        bufRequestNum.setCellValueFactory(new PropertyValueFactory<>("requestNum"));

        deviceNum.setCellValueFactory(new PropertyValueFactory<>("deviceNum"));
        releaseTime.setCellValueFactory(new PropertyValueFactory<>("releaseTime"));
        downtime.setCellValueFactory(new PropertyValueFactory<>("downtime"));
        sNum.setCellValueFactory(new PropertyValueFactory<>("sourceNum"));
        rNum.setCellValueFactory(new PropertyValueFactory<>("requestNum"));
        gTime.setCellValueFactory(new PropertyValueFactory<>("genTime"));
    }

    public void printInfo(List<String> eventCalendar, List<List<SourceResult>> sourcesStates, List<List<BufferResult>> bufferStates, List<List<DeviceResult>> devicesStates) {
        step = 0;

        this.eventCalendar = eventCalendar;
        events.appendText(eventCalendar.get(step) + '\n');

        this.sourcesStates = sourcesStates;
        ObservableList<SourceResult> sourceResults = FXCollections.observableArrayList();
        sourceResults.addAll(sourcesStates.get(step));
        sources.setItems(sourceResults);

        this.bufferStates = bufferStates;
        ObservableList<BufferResult> bufferResults = FXCollections.observableArrayList();
        bufferResults.addAll(bufferStates.get(step));
        buffer.setItems(bufferResults);

        this.devicesStates = devicesStates;
        ObservableList<DeviceResult> deviceResults = FXCollections.observableArrayList();
        deviceResults.addAll(devicesStates.get(step));
        devices.setItems(deviceResults);
    }

    @FXML
    void nextStep() {
        if (step < sourcesStates.size()) {
            ++step;

            events.appendText(eventCalendar.get(step) + '\n');

            sources.refresh();
            ObservableList<SourceResult> sourceResults = FXCollections.observableArrayList();
            sourceResults.addAll(sourcesStates.get(step));
            sources.setItems(sourceResults);

            buffer.refresh();
            ObservableList<BufferResult> bufferResults = FXCollections.observableArrayList();
            bufferResults.addAll(bufferStates.get(step));
            buffer.setItems(bufferResults);

            devices.refresh();
            ObservableList<DeviceResult> deviceResults = FXCollections.observableArrayList();
            deviceResults.addAll(devicesStates.get(step));
            devices.setItems(deviceResults);
        }
    }

    @FXML
    void openMenu() {
        eventCalendar.clear();
        sources.refresh();
        buffer.refresh();
        devices.refresh();
        app.openMenu();
    }

    public void setApp(App app) {
        this.app = app;
    }
}
