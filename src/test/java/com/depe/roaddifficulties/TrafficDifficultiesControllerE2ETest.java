
package com.depe.roaddifficulties;

import com.depe.roaddifficulties.model.Results;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrafficDifficultiesControllerE2ETest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void httpGetReturnsAllCurrentDifficulties(){
        Results results = testRestTemplate
                .getForObject("http://localhost:" + port + "/api/v1/traffic-difficulties" , Results.class);
        assertThat(results).isNotNull();
    }
    @Test
    void httpGetReturnsAllCurrentDifficultiesByRoad(){
        Results results = testRestTemplate
                .getForObject("http://localhost:" + port + "/api/v1/traffic-difficulties/road/a2" , Results.class);
        assertThat(results).isNotNull();
    }

    @Test
    void httpGetReturnsAllCurrentDifficultiesByVoivodeship(){
        Results results = testRestTemplate
                .getForObject("http://localhost:" + port + "/api/v1/traffic-difficulties/voivodeship/lubelskie" , Results.class);
        assertThat(results).isNotNull();
    }

    @Test
    void httpGetReturnsAllCurrentDifficultiesByLocationAndDistance(){
        Map<String, Double> vars = new HashMap<>();
        vars.put("latitude", 52.769575);
        vars.put("longitude", 18.848718);
        vars.put("distance", 50.0);
        Results results = testRestTemplate
                .getForObject("http://localhost:" + port + "/api/v1/traffic-difficulties/map" , Results.class, vars);
        assertThat(results).isNotNull();
    }

}
