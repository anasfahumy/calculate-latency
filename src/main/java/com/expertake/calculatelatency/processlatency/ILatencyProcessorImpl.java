package com.expertake.calculatelatency.processlatency;

import com.expertake.calculatelatency.vo.AverageLatencyVO;
import com.expertake.calculatelatency.vo.LatencyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author afahumy
 * @since 7/1/2022
 */
@Service
public class ILatencyProcessorImpl implements ILatencyProcessor {

    private final Logger logger = LoggerFactory.getLogger(ILatencyProcessorImpl.class);

    @Override
    public List<AverageLatencyVO> process(List<LatencyVO> latencyList) {
        logger.info("processing latencyList");
        var averageLatencyVOMap = new HashMap<Integer, AverageLatencyVO>();
        for(var latency: latencyList) {
            if (averageLatencyVOMap.containsKey(latency.getServiceId())) {
                averageLatencyVOMap.put(latency.getServiceId(), computeExistingWithNew(averageLatencyVOMap.get(latency.getServiceId()), latency));
            } else {
                averageLatencyVOMap.put(latency.getServiceId(), new AverageLatencyVO(latency.getServiceId(), 1, latency.getMilliSecondsDelay()));
            }
        }
        return averageLatencyVOMap.entrySet().stream().map(entry -> getAverageLatencyVO(entry)).collect(Collectors.toList());

    }

    /**
     * RoundsUp the average latency of the entry
     * @param entry
     * @return entry
     */
    private AverageLatencyVO getAverageLatencyVO(Map.Entry<Integer, AverageLatencyVO> entry) {
        entry.getValue().setAverageResponseTimeMs(Math.round(entry.getValue().getAverageResponseTimeMs()));
        return entry.getValue();
    }

    /**
     * updates averageLatencyVO with the new latencyVO
     * computes the average of both values
     * @param averageLatencyVO existing average
     * @param latency new value
     * @return computed new AverageLatencyVO
     */
    private AverageLatencyVO computeExistingWithNew(AverageLatencyVO averageLatencyVO, LatencyVO latency) {
        averageLatencyVO.setNumberOfRequests(averageLatencyVO.getNumberOfRequests() + 1);
        averageLatencyVO.setAverageResponseTimeMs((averageLatencyVO.getAverageResponseTimeMs() + latency.getMilliSecondsDelay()) / 2);
        return averageLatencyVO;
    }
}
