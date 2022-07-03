package com.expertake.calculatelatency.vo;

/**
 * @author afahumy
 * @since 7/1/2022
 */
public class LatencyVO implements ApplicationVO {
    private int requestId;
    private int serviceId;
    private String date;
    private int milliSecondsDelay;

    public LatencyVO() {
    }

    public LatencyVO(int requestId, int serviceId, String date, int milliSecondsDelay) {
        this.requestId = requestId;
        this.serviceId = serviceId;
        this.date = date;
        this.milliSecondsDelay = milliSecondsDelay;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMilliSecondsDelay() {
        return milliSecondsDelay;
    }

    public void setMilliSecondsDelay(int milliSecondsDelay) {
        this.milliSecondsDelay = milliSecondsDelay;
    }
}
