package com.expertake.calculatelatency.latencyapi;

import com.expertake.calculatelatency.vo.LatencyVO;

import java.util.List;

/**
 * @author afahumy
 * @since 7/1/2022
 */
public interface IApiLatencyServer {
    /**
     * Get Latency details for the given date
     * @param date
     * @return list of LatencyVO
     */
    List<LatencyVO> getLatency(String date);
}
