package com.expertake.calculatelatency.latencyapi;

import com.expertake.calculatelatency.exception.LatencyServiceException;
import com.expertake.calculatelatency.http.IHttpService;
import com.expertake.calculatelatency.vo.LatencyVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author afahumy
 * @since 7/1/2022
 */
@Service
public class ApiLatencyServerImpl implements IApiLatencyServer {

    @Value("${request.data.api.url}")
    private String urlEndpoint;
    private IHttpService httpService;

    Logger logger = LoggerFactory.getLogger(ApiLatencyServerImpl.class);

    public ApiLatencyServerImpl(IHttpService httpService) {
        this.httpService = httpService;
    }

    @Override
    public List<LatencyVO> getLatency(String date) {
        try {
            var response = httpService.getRequest(urlEndpoint + date);
            if (response.getStatus() == 200) {
                return mapResponse(response.getBody());
            } else {
                logger.error("HTTP request is not Ok(200) response -> {}", response);
                throw new LatencyServiceException("HTTP request returns with "+ response.getStatus() + " response code");
            }
        } catch (IOException | InterruptedException ex) {
            logger.error("Http get request failed for the url {}", urlEndpoint + date);
            throw new LatencyServiceException("Http get request failed", ex);
        }
    }

    /**
     * Maps string body to  LatencyVO list
     * @param body string
     * @return list of latencyVO
     */
    private List<LatencyVO> mapResponse(String body) {
        var mapper = new ObjectMapper();
        try {
            return mapper.readValue(body, mapper.getTypeFactory().constructCollectionType(List.class, LatencyVO.class));
        } catch (IOException ex) {
            logger.error("Unable to map {} to LatencyVO", body);
            throw new LatencyServiceException("Unable to map LatencyVO", ex);
        }
    }

}
