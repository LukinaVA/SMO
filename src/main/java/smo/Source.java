package smo;// Бесконечный источник
// Пуассоновский закон распределения

public class Source {
    private final int sourceNum;
    private final double lambda;

    private int requestNum = 0;

    private double generationTime = 0.0;
    private Request request;

    public Source(int sourceNum, double lambda) {
        this.sourceNum = sourceNum;
        this.lambda = lambda;
    }

    public void generateNewRequest() {
        ++requestNum;
        generationTime += -1 / lambda * Math.log(Math.random());
        request = new Request(generationTime, requestNum, sourceNum);
    }

    public double getGenerationTime() {
        return generationTime;
    }

    public Request getRequest() {
        return request;
    }

    public int getRequestNum() {
        return requestNum;
    }

    public int getSourceNum() {
        return sourceNum;
    }
}
