package smo;

public class BufferResult {
    private int position;
    private double genTime;
    private int sourceNum;
    private int requestNum;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getGenTime() {
        return genTime;
    }

    public void setGenTime(double genTime) {
        this.genTime = genTime;
    }

    public int getSourceNum() {
        return sourceNum;
    }

    public void setSourceNum(int sourceNum) {
        this.sourceNum = sourceNum;
    }

    public int getRequestNum() {
        return requestNum;
    }

    public void setRequestNum(int requestNum) {
        this.requestNum = requestNum;
    }
}
