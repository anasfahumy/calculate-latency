package com.expertake.calculatelatency.processlatency;

import com.expertake.calculatelatency.vo.LatencyVO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author afahumy
 * @since 7/2/2022
 */
class ILatencyProcessorImplTest {

    private static ILatencyProcessorImpl latencyProcessor;

    @BeforeAll
    static void initialize(){
        latencyProcessor = new ILatencyProcessorImpl();
    }

    @Test
    void processTest() {
        var latencyList = List.of(new LatencyVO(1, 1, "2022-01-01", 10),
                new LatencyVO(2, 1, "2022-01-01", 20));

        var averageLatencyVO = latencyProcessor.process(latencyList);
        assertEquals(averageLatencyVO.get(0).getAverageResponseTimeMs(), 15);

    }

}