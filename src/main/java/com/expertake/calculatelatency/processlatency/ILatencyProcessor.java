package com.expertake.calculatelatency.processlatency;

import com.expertake.calculatelatency.vo.AverageLatencyVO;
import com.expertake.calculatelatency.vo.LatencyVO;

import java.util.List;

/**
 * @author afahumy
 * @since 7/1/2022
 */
public interface ILatencyProcessor {

    /**
     * Processes the List<LatencyVO>  and calculate
     * 1) the number of request for each service
     * 2) average latency time
     * @param latencyList List<LatencyVO>
     * @return calculated AverageLatencyVO list
     */
    List<AverageLatencyVO> process(List<LatencyVO> latencyList);
}
