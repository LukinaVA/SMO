package smo;

public class Config {
    private int sourceNum;
    private double flowIntensity;
    private int deviceNum;
    private double a;
    private double b;
    private int bufferSize;
    private int requestsNum;

    public Config(int sourceNum, double flowIntensity, int deviceNum, double a, double b, int bufferSize, int requestsNum) {
        this.sourceNum = sourceNum;
        this.flowIntensity = flowIntensity;
        this.deviceNum = deviceNum;
        this.a = a;
        this.b = b;
        this.bufferSize = bufferSize;
        this.requestsNum = requestsNum;
    }

    public int getSourceNum() {
        return sourceNum;
    }

    public void setSourceNum(int sourceNum) {
        this.sourceNum = sourceNum;
    }

    public double getFlowIntensity() {
        return flowIntensity;
    }

    public void setFlowIntensity(double flowIntensity) {
        this.flowIntensity = flowIntensity;
    }

    public int getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(int deviceNum) {
        this.deviceNum = deviceNum;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public int getRequestsNum() {
        return requestsNum;
    }

    public void setRequestsNum(int requestsNum) {
        this.requestsNum = requestsNum;
    }
}
