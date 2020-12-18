package smo;

import java.util.ArrayList;
import java.util.List;

public class ProductionManager {
    private final Buffer buffer;
    private final ArrayList<Source> sources;
    private final SelectionManager selectionManager;

    private Source currentSource;
    private Request currentRequest;

    public ProductionManager(Buffer buffer, ArrayList<Source> sources, SelectionManager selectionManager) {
        this.buffer = buffer;
        this.sources = sources;
        this.selectionManager = selectionManager;
    }

    public void initSources() {
        for (Source s : sources) {
            s.generateNewRequest();
        }
    }

    public void generateNewRequest() {
        currentSource.generateNewRequest();
    }

    public void setCurrentSource() {
        Source source = sources.get(0);
        for (Source s : sources) {
            if (s.getGenerationTime() < source.getGenerationTime()) {
                source = s;
            }
        }

        currentSource = source;
    }

    public Source getCurrentSource() {
        return currentSource;
    }

    public void getRequest() {
        currentRequest = currentSource.getRequest();
    }

    public void sendRequest(List<String> eventCalendar) {
        if (buffer.isEmpty() && selectionManager.hasFreeDevice(currentRequest.getGenerationTime())) {
            selectionManager.getRequestFromSource(currentRequest);
            selectionManager.sendRequestToDevice();

            eventCalendar.add("Взяли заявку от " + currentSource.getSourceNum() + " источника.\n"
                    + "Отправили обрабатываться на " + selectionManager.getCurrentDevice().getDeviceNum() + " прибор.\n"
                    + "Сгенерировали следующую заявку у данного источника.\n"
                    + "------------");
        } else {
            if (buffer.hasFreeSpaces()) {
                buffer.putRequest(currentRequest);
                eventCalendar.add("Взяли заявку от " + currentSource.getSourceNum() + " источника.\n"
                        + "Отправили в буфер.\n"
                        + "Сгенерировали следующую заявку у данного источника.\n"
                        + "------------");
            } else {
                buffer.replaceOldestRequest(currentRequest);
                eventCalendar.add("Взяли заявку от " + currentSource.getSourceNum() + " источника.\n"
                        + "Выбили самую стаурую заявку из буфера.\n"
                        + "На её место поставили новую.\n"
                        + "Сгенерировали следующую заявку у данного источника.\n"
                        + "------------");
            }
        }
    }

    public ArrayList<Source> getSources() {
        return sources;
    }

    public Request getCurrentRequest() {
        return currentRequest;
    }
}
