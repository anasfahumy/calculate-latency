package com.expertake.calculatelatency.controller;

import com.expertake.calculatelatency.service.LatencyService;
import com.expertake.calculatelatency.vo.ApplicationVO;
import com.expertake.calculatelatency.vo.LatencyResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author afahumy
 * @since 7/2/2022
 */
@RestController
public class LatencyController {

    private LatencyService latencyService;

    public LatencyController(LatencyService latencyService) {
        this.latencyService = latencyService;
    }

    @GetMapping("/latenciesSequential")
    public ResponseEntity<LatencyResponseVO> getLatencyDetails(@RequestParam("startDate") String startDate,
                                                               @RequestParam("endDate") String endDate) {
        var response = latencyService.getLatencyDetails(startDate, endDate);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/latencies")
    public ResponseEntity<LatencyResponseVO> getLatencyDetailsAsync(@RequestParam("startDate") String startDate,
                                                 @RequestParam("endDate") String endDate) throws ExecutionException, InterruptedException {
        var response = latencyService.getLatencyDetailsParallel(startDate, endDate);
        return ResponseEntity.ok(response);
    }

}
