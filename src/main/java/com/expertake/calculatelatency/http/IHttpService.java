package com.expertake.calculatelatency.http;

import com.expertake.calculatelatency.vo.HttpResponse;

import java.io.IOException;

/**
 * @author afahumy
 * @since 7/1/2022
 */
public interface IHttpService {
    /***
     * Invokes Http GET request for external API
     * @param urlEndpoint url for the API GET request
     * @return HttpResponse wrapper object with body and response code.
     * @throws IOException
     * @throws InterruptedException
     */
    HttpResponse getRequest(String urlEndpoint) throws IOException, InterruptedException;
}
