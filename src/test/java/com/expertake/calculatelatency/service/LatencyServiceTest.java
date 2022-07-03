package com.expertake.calculatelatency.service;

import com.expertake.calculatelatency.latencyapi.ApiLatencyServerImpl;
import com.expertake.calculatelatency.latencyapi.IApiLatencyServer;
import com.expertake.calculatelatency.processlatency.ILatencyProcessor;
import com.expertake.calculatelatency.processlatency.ILatencyProcessorImpl;
import com.expertake.calculatelatency.vo.AverageLatencyVO;
import com.expertake.calculatelatency.vo.LatencyVO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author afahumy
 * @since 7/2/2022
 */
class LatencyServiceTest {

    private static LatencyService latencyService;
    private static IApiLatencyServer restApiLatency;
    private static ILatencyProcessor latencyProcessor;

    @BeforeAll
    static void initialize() {
        restApiLatency = Mockito.mock(ApiLatencyServerImpl.class);
        latencyProcessor = Mockito.mock(ILatencyProcessorImpl.class);
        latencyService = new LatencyService(restApiLatency, latencyProcessor);
    }

    @Test
    void getLatencyDetailsTest() {
        var startDate = "2021-10-01";
        var endDate = "2021-10-02";
        var latencyList1 = List.of(new LatencyVO(1, 1, startDate, 10));
        var latencyList2 = List.of(new LatencyVO(2, 1, endDate, 20));
        var averageLatencyVOS = List.of(new AverageLatencyVO(1, 2, 15));

        Mockito.when(restApiLatency.getLatency(startDate)).thenReturn(latencyList1);
        Mockito.when(restApiLatency.getLatency(endDate)).thenReturn(latencyList2);
        Mockito.when(latencyProcessor.process(Mockito.anyList())).thenReturn(averageLatencyVOS);
        var latencyResponseVO = latencyService.getLatencyDetails(startDate, endDate);

        assertEquals(latencyResponseVO.getPeriod(), List.of(startDate, endDate));
        assertEquals(latencyResponseVO.getAverageLatencies(), averageLatencyVOS);

    }


}