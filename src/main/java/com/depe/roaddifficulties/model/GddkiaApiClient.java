package com.depe.roaddifficulties.model;

import com.depe.roaddifficulties.utils.XMLUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

@Component
public class GddkiaApiClient {

    @Value("${gddkia.api.url}")
    private String url;
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public Results getResults() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .timeout(Duration.of(10, SECONDS))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200){
                return XMLUtils.deserializeResultsFromXML(response.body());
            }else{
                throw new RuntimeException();
            }
        }
        catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException();
        }
    }
}
