package com.expertake.calculatelatency.service;

import com.expertake.calculatelatency.exception.LatencyServiceException;
import com.expertake.calculatelatency.latencyapi.IApiLatencyServer;
import com.expertake.calculatelatency.processlatency.ILatencyProcessor;
import com.expertake.calculatelatency.vo.LatencyResponseVO;
import com.expertake.calculatelatency.vo.LatencyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author afahumy
 * @since 7/1/2022
 */
@Service
public class LatencyService {

    private IApiLatencyServer restApiLatency;
    private ILatencyProcessor latencyProcessor;
    Logger logger = LoggerFactory.getLogger(LatencyService.class);

    LatencyService(IApiLatencyServer restApiLatency, ILatencyProcessor latencyProcessor) {
        this.restApiLatency = restApiLatency;
        this.latencyProcessor = latencyProcessor;
    }

    /**
     * Approach (1) - without completableFuture
     * to get latency details for the provided period. This method will trigger API calls to the server sequentially
     * this method will cost more time
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public LatencyResponseVO getLatencyDetails(String startDate, String endDate) {
        var start = LocalDate.parse(startDate);
        var end = LocalDate.parse(endDate);
        var counter = start;
        if (validPeriod(start, end)) {
            var latencyList = new ArrayList<LatencyVO>();
            while (counter.isBefore(end) || counter.equals(end)) {
                logger.info("getting latency for {}", counter);
                latencyList.addAll(restApiLatency.getLatency(counter.toString()));
                counter = counter.plusDays(1);
            }
            return new LatencyResponseVO(Arrays.asList(startDate, endDate), latencyProcessor.process(latencyList));
        } else {
            logger.error("invalid dates startDate -> {}, endDate -> {}", startDate, endDate);
            throw new LatencyServiceException("Invalid dates provided");
        }
    }

    /**
     * Approach (2) - with completableFuture
     * to get latency details for the provided period. This method will trigger API calls to the server parallelly
     * this method will cost less time
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public LatencyResponseVO getLatencyDetailsParallel(String startDate, String endDate) {
        var start = LocalDate.parse(startDate);
        var end = LocalDate.parse(endDate);
        var counter = start;
        if (validPeriod(start, end)) {
            var latencyList = new ArrayList<LatencyVO>();
            var completableFutureList = new ArrayList<CompletableFuture<List<LatencyVO>>>();
            while (counter.isBefore(end) || counter.equals(end)) {
                logger.info("getting latency for {}", counter);
                var finalCounter = counter;
                completableFutureList.add(CompletableFuture.supplyAsync(() -> restApiLatency.getLatency(finalCounter.toString())));
                counter = counter.plusDays(1);
            }
            try {
                for (CompletableFuture<List<LatencyVO>> future : completableFutureList) {
                    latencyList.addAll(future.get());
                }
            } catch (ExecutionException | InterruptedException ex) {
                logger.error("Parallel Execution failed", ex);
                throw new LatencyServiceException("Parallel Execution failed", ex);
            }
            return new LatencyResponseVO(Arrays.asList(startDate, endDate), latencyProcessor.process(latencyList));
        } else {
            logger.error("invalid dates startDate -> {}, endDate -> {}", startDate, endDate);
            throw new LatencyServiceException("Invalid dates provided");
        }
    }

    /**
     * validates date range is valid
     *
     * @param start
     * @param end
     * @return true is valid date range
     */
    private boolean validPeriod(LocalDate start, LocalDate end) {
        return Objects.nonNull(start) && Objects.nonNull(end) && start.isBefore(end);
    }
}
