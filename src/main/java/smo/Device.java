package smo;// равномерный закон распределения времени обслуживания

public class Device {
    private final int deviceNum;
    private final double a;
    private final double b;
    private double startTime = 0;
    private double releaseTime = 0;
    private Request currentRequest = null;

    private double downtime = 0;

    public Device(int deviceNum, double a, double b) {
        this.deviceNum = deviceNum;
        this.a = a;
        this.b = b;
    }

    public Request handleRequest(Request request) {
        this.currentRequest = request;
        if (request.getGenerationTime() > startTime) {
            downtime += request.getGenerationTime() - startTime;
            startTime = request.getGenerationTime();
        } else {
            request.setWaitTime(startTime - request.getGenerationTime());
        }
        double handleTime = (b - a) * Math.random() + a;
        request.setHandleTime(handleTime);
        releaseTime = startTime + handleTime;
        startTime = releaseTime;

        return request;
    }

    public double getReleaseTime() {
        return releaseTime;
    }

    public double getDowntime() {
        return downtime;
    }

    public int getDeviceNum() {
        return deviceNum;
    }

    public Request getCurrentRequest() {
        return currentRequest;
    }
}
