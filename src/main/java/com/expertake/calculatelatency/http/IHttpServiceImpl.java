package com.expertake.calculatelatency.http;

import com.expertake.calculatelatency.latencyapi.ApiLatencyServerImpl;
import com.expertake.calculatelatency.vo.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

/**
 * @author afahumy
 * @since 7/1/2022
 */
@Service
public class IHttpServiceImpl implements IHttpService {

    Logger logger = LoggerFactory.getLogger(IHttpServiceImpl.class);
    @Override
    public HttpResponse getRequest(String urlEndpoint) throws IOException, InterruptedException {
        logger.info("HTTP Request for ->"+ urlEndpoint);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(urlEndpoint))
                .build();
        var response = client.send(request, BodyHandlers.ofString());
        return new HttpResponse(response.statusCode(), response.body());
    }
}
