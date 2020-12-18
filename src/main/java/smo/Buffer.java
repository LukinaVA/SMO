package smo;

import java.util.ArrayList;

public class Buffer {
    private final int bufferSize;
    private int requestNum;
    private final ArrayList<Request> requestQueue;
    private final int[] rejectedRequests;
    private Request lastRejectedRequest;

    public Buffer(int bufferSize, int sourceNum) {
        this.bufferSize = bufferSize;
        this.requestQueue = new ArrayList<>(bufferSize);
        this.requestNum = 0;
        this.rejectedRequests = new int[sourceNum];
    }

    public boolean isEmpty() {
        return requestQueue.isEmpty();
    }

    public boolean hasFreeSpaces() {
        return requestNum < bufferSize;
    }

    public void putRequest(Request request) {
        requestQueue.add(request);
        ++requestNum;
    }

    public void replaceOldestRequest(Request request) {
        int oldestRequestIndex = 0;
        double time = requestQueue.get(0).getGenerationTime();
        for (Request req : requestQueue) {
            if (req.getGenerationTime() < time) {
                oldestRequestIndex = requestQueue.indexOf(req);
                time = req.getGenerationTime();
            }
        }

        lastRejectedRequest = requestQueue.get(oldestRequestIndex);
        ++rejectedRequests[lastRejectedRequest.getSourceNumber()];

        requestQueue.set(oldestRequestIndex,request);
    }

    Request getLastRequest() {
        Request lastRequest = requestQueue.get(0);
        double lastRequestTime = lastRequest.getGenerationTime();
        for (Request req: requestQueue) {
            if (req.getGenerationTime() > lastRequestTime) {
                lastRequest = req;
                lastRequestTime = lastRequest.getGenerationTime();
            }
        }

        if (requestNum == 1 || (requestQueue.indexOf(lastRequest) == requestNum - 1)) {
            requestQueue.remove(lastRequest);
        } else {
            for (int i = requestQueue.indexOf(lastRequest); i < requestNum - 1; ++i) {
                requestQueue.set(i, requestQueue.get(i + 1));
            }
            requestQueue.remove(requestNum - 1);
        }

        --requestNum;
        return lastRequest;
    }

    public int[] getRejectedRequests() {
        return rejectedRequests;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public ArrayList<Request> getRequestQueue() {
        return requestQueue;
    }

    public Request getLastRejectedRequest() {
        return lastRejectedRequest;
    }
}
