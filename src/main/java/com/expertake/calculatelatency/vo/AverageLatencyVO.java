package com.expertake.calculatelatency.vo;

/**
 * @author afahumy
 * @since 7/2/2022
 */
public class AverageLatencyVO {
    private int serviceId;
    private int numberOfRequests;
    private float averageResponseTimeMs;

    public AverageLatencyVO(int serviceId, int numberOfRequests, float averageResponseTimeMs) {
        this.serviceId = serviceId;
        this.numberOfRequests = numberOfRequests;
        this.averageResponseTimeMs = averageResponseTimeMs;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    public void setNumberOfRequests(int numberOfRequests) {
        this.numberOfRequests = numberOfRequests;
    }

    public float getAverageResponseTimeMs() {
        return averageResponseTimeMs;
    }

    public void setAverageResponseTimeMs(float averageResponseTimeMs) {
        this.averageResponseTimeMs = averageResponseTimeMs;
    }
}
