package com.expertake.calculatelatency.vo;

import java.util.List;

/**
 * @author afahumy
 * @since 7/1/2022
 */
public class LatencyResponseVO implements ApplicationVO {
    List<String> period;
    List<AverageLatencyVO> averageLatencies;

    public LatencyResponseVO(List<String> period, List<AverageLatencyVO> averageLatencies) {
        this.period = period;
        this.averageLatencies = averageLatencies;
    }

    public List<String> getPeriod() {
        return period;
    }

    public void setPeriod(List<String> period) {
        this.period = period;
    }

    public List<AverageLatencyVO> getAverageLatencies() {
        return averageLatencies;
    }

    public void setAverageLatencies(List<AverageLatencyVO> averageLatencies) {
        this.averageLatencies = averageLatencies;
    }
}
