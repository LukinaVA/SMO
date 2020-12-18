package smo;
// выбор заявки из буфера LIFO

import java.util.ArrayList;

public class SelectionManager {
    private final Buffer buffer;
    private final ArrayList<Device> devices;

    private Device currentDevice;
    private Request currentRequest;

    private final double[] waitTime;
    private final double[] handleTime;

    public SelectionManager(Buffer buffer, ArrayList<Device> devices, int sourceNum) {
        this.buffer = buffer;
        this.devices = devices;
        this.waitTime = new double[sourceNum];
        this.handleTime = new double[sourceNum];
    }

    public boolean hasFreeDevice(double currentTime) {
        for (Device device : devices) {
            if (device.getReleaseTime() < currentTime) {
                return true;
            }
        }
        return false;
    }

    public void setCurrentDevice() {
        currentDevice = devices.get(0);
        for (Device device : devices) {
            if (device.getReleaseTime() < currentDevice.getReleaseTime()) {
                currentDevice = device;
            }
        }
    }

    public Device getCurrentDevice() {
        return currentDevice;
    }

    public void getRequestFromSource(Request request) {
        currentRequest = request;
    }

    public void getRequestFromBuffer() {
        currentRequest = buffer.getLastRequest();
    }

    public void sendRequestToDevice() {
        Request request = currentDevice.handleRequest(currentRequest);
        waitTime[request.getSourceNumber()] += request.getWaitTime();
        handleTime[request.getSourceNumber()] += request.getHandleTime();
    }

    public double getLastReleaseTime() {
        double time = devices.get(0).getReleaseTime();
        for (Device device : devices) {
            if (device.getReleaseTime() > time) {
                time = device.getReleaseTime();
            }
        }

        return time;
    }

    public double[] getWaitTime() {
        return waitTime;
    }

    public double[] getHandleTime() {
        return handleTime;
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public Request getCurrentRequest() {
        return currentRequest;
    }
}
