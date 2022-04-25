package com.depe.roaddifficulties.traffic_difficulty;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TrafficDifficultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void httpGet_ReturnsResults() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                    .get("/api/v1/traffic-difficulties")
                ).andExpect(status().is2xxSuccessful());
    }

    @Test
    void httpGet_givenURIWithPathVariable_returnsResultsByRoadAndResponseStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/traffic-difficulties/road/{road}","a2")
        ).andExpect(status().is2xxSuccessful());
    }

    @Test
    void httpGet_givenURIWithPathVariable_returnsAllCurrentDifficultiesByVoivodeshipAndResponseStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/traffic-difficulties/voivodeship/{voivodeship}","lubelskie")
        ).andExpect(status().is2xxSuccessful());
    }
    @Test
    void httpGet_givenURIWithQueryParams_returnsAllCurrentDifficultiesByLocationAndDistanceAndResponseStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/traffic-difficulties/map")
                .param("latitude","52.769575")
                .param("longitude", "18.848718")
                .param("distance", "100.0")
        ).andExpect(status().is2xxSuccessful());
    }
}
