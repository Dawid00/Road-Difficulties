package com.depe.roaddifficulties.client;

import com.depe.roaddifficulties.exceptions.InternalServerException;
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

    public String getResponse() {
        try {
            var response = sendRequest();
            if(response.statusCode() == 200){
                return response.body();
            }
            else{
                throw new InternalServerException();
            }
        }
        catch (IOException | InterruptedException | URISyntaxException e) {
            throw new InternalServerException();
        }
    }

    private HttpResponse<String> sendRequest() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
