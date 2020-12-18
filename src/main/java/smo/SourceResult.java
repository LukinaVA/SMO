package smo;

public class SourceResult {
    private int sourceNum;
    //auto mode
    private int requestNum;
    private double failureProbability;
    private double averageTimeInSystem;
    private double averageWaitTime;
    private double averageHandleTime;
    //step mode
    private double genTime;
    private int reqNum;
    private int rejReqNum;

    public void setSourceNum(int sourceNum) {
        this.sourceNum = sourceNum;
    }

    public void setRequestNum(int requestNum) {
        this.requestNum = requestNum;
    }

    public void setFailureProbability(double failureProbability) {
        this.failureProbability = failureProbability;
    }

    public void setAverageTimeInSystem(double averageTimeInSystem) {
        this.averageTimeInSystem = averageTimeInSystem;
    }

    public void setAverageWaitTime(double averageWaitTime) {
        this.averageWaitTime = averageWaitTime;
    }

    public void setAverageHandleTime(double averageHandleTime) {
        this.averageHandleTime = averageHandleTime;
    }

    public int getSourceNum() {
        return sourceNum;
    }

    public int getRequestNum() {
        return requestNum;
    }

    public double getFailureProbability() {
        return failureProbability;
    }

    public double getAverageTimeInSystem() {
        return averageTimeInSystem;
    }

    public double getAverageWaitTime() {
        return averageWaitTime;
    }

    public double getAverageHandleTime() {
        return averageHandleTime;
    }

    public double getGenTime() {
        return genTime;
    }

    public void setGenTime(double genTime) {
        this.genTime = genTime;
    }

    public int getReqNum() {
        return reqNum;
    }

    public void setReqNum(int reqNum) {
        this.reqNum = reqNum;
    }

    public int getRejReqNum() {
        return rejReqNum;
    }

    public void setRejReqNum(int rejReqNum) {
        this.rejReqNum = rejReqNum;
    }
}
