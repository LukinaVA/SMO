package smo;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import controller.MenuController;
import controller.AutoModeController;
import controller.StepModeController;
import javafx.application.Application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    private Stage primaryStage;

    private MenuController menuController;
    private AutoModeController autoModeController;
    private StepModeController stepModeController;

    private int currentRequestsNum;
    private int requestsNum;
    private ProductionManager productionManager;
    private SelectionManager selectionManager;
    private Buffer buffer;
    private Statistics statistics;

    private List<String> eventCalendar;
    private List<List<SourceResult>> sourcesStates;
    private List<List<BufferResult>> bufferStates;
    private List<List<DeviceResult>> devicesStates;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        openMenu();
        primaryStage.show();
    }

    public void init(int sourceNum, double flowIntensity, int deviceNum, double a, double b, int bufferSize, int rNum) {
        ArrayList<Source> sources = new ArrayList<>(sourceNum);
        for (int i = 0; i < sourceNum; ++i) {
            sources.add(new Source(i, flowIntensity));
        }

        ArrayList<Device> devices = new ArrayList<>(deviceNum);
        for (int i = 0; i < deviceNum; ++i) {
            devices.add(new Device(i, a, b));
        }

        buffer = new Buffer(bufferSize, sourceNum);

        selectionManager = new SelectionManager(buffer, devices, sourceNum);
        productionManager = new ProductionManager(buffer, sources, selectionManager);

        productionManager.initSources();
        currentRequestsNum = 0;
        requestsNum = rNum;
        statistics = new Statistics(productionManager, selectionManager, buffer);

        eventCalendar = new ArrayList<>();
        sourcesStates = new ArrayList<>();
        bufferStates = new ArrayList<>();
        devicesStates = new ArrayList<>();
    }

    public void runSimulation() {
        while (currentRequestsNum < requestsNum ) {
            productionManager.setCurrentSource();
            selectionManager.setCurrentDevice();

            if (!buffer.isEmpty() && (selectionManager.getCurrentDevice().getReleaseTime() <
                    productionManager.getCurrentSource().getGenerationTime())) {
                selectionManager.getRequestFromBuffer();
                selectionManager.sendRequestToDevice();
                eventCalendar.add(selectionManager.getCurrentDevice().getDeviceNum()+" прибор свободен.\n" +
                        "Взяли заявку из буфера.\n" +
                        "Отправили обрабатываться на прибор.\n" +
                        "------------");
            } else {
                productionManager.getRequest();
                productionManager.sendRequest(eventCalendar);
                productionManager.generateNewRequest();
                ++currentRequestsNum;
            }
            sourcesStates.add(statistics.getSourcesCurrState());
            bufferStates.add(statistics.getBufferCurrState());
            devicesStates.add(statistics.getDevicesCurrState());
        }
        while (!buffer.isEmpty()) {
            selectionManager.setCurrentDevice();
            selectionManager.getRequestFromBuffer();
            selectionManager.sendRequestToDevice();
            eventCalendar.add(selectionManager.getCurrentDevice().getDeviceNum()+" прибор свободен.\n" +
                    "Взяли заявку из буфера.\n" +
                    "Отправили обрабатываться на прибор.\n" +
                    "------------");
            sourcesStates.add(statistics.getSourcesCurrState());
            bufferStates.add(statistics.getBufferCurrState());
            devicesStates.add(statistics.getDevicesCurrState());
        }
    }

    public void openMenu() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
        try {
            primaryStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Menu");
        MenuController controller = loader.getController();
        this.menuController = controller;
        controller.setApp(this);
    }

    public void openAutoMode() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/autoMode.fxml"));
        try {
            primaryStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Auto Mode");
        this.autoModeController = loader.getController();
        primaryStage.show();
        autoModeController.setApp(this);
        autoModeController.printInfo(statistics);
    }

    public void openStepMode() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stepMode.fxml"));
        try {
            primaryStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Step Mode");
        this.stepModeController = loader.getController();
        primaryStage.show();
        stepModeController.setApp(this);
        stepModeController.printInfo(eventCalendar, sourcesStates, bufferStates, devicesStates);
    }
}
