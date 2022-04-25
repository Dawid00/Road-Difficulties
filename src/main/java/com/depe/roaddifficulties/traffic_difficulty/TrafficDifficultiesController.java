package com.depe.roaddifficulties.traffic_difficulty;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/traffic-difficulties")
class TrafficDifficultiesController {

    private final TrafficDifficultiesService trafficDifficultiesService;

    TrafficDifficultiesController(TrafficDifficultiesService trafficDifficultiesService) {
        this.trafficDifficultiesService = trafficDifficultiesService;
    }

    @GetMapping()
    ResponseEntity<Results> getResults(){
        return ResponseEntity.ok(trafficDifficultiesService.getResults());
    }

    @GetMapping("/road/{road}")
    ResponseEntity<Results> getResultsByRoad(@PathVariable(value = "road") String roadName){
        return ResponseEntity.ok(trafficDifficultiesService.getResultsByRoadName(roadName));
    }

    @GetMapping("/voivodeship/{voivodeship}")
    ResponseEntity<Results> getResultsByVoivodeship(@PathVariable String voivodeship){
        return ResponseEntity.ok(trafficDifficultiesService.getResultsByVoivodeship(voivodeship));
    }

    @GetMapping("/map")
    ResponseEntity<Results> getResultsByMap(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Double distance){
        return ResponseEntity.ok(trafficDifficultiesService.getResultsByDistance(latitude, longitude, distance));
    }

}
