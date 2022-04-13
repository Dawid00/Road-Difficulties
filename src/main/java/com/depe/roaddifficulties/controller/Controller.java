package com.depe.roaddifficulties.controller;

import com.depe.roaddifficulties.model.Results;
import com.depe.roaddifficulties.service.RoadDifficultiesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/traffic-difficulties")
class Controller {

    private final RoadDifficultiesService roadDifficultiesService;

    Controller(RoadDifficultiesService roadDifficultiesService) {
        this.roadDifficultiesService = roadDifficultiesService;
    }

    @GetMapping()
    ResponseEntity<Results> getResults(){
        return ResponseEntity.ok(roadDifficultiesService.getResults());
    }

    @GetMapping("/road/{road}")
    ResponseEntity<Results> getResultsByRoad(@PathVariable String road){
        return ResponseEntity.ok(roadDifficultiesService.getResultsByRoad(road));
    }

    @GetMapping("/voivodeship/{voivodeship}")
    ResponseEntity<Results> getResultsByVoivodeship(@PathVariable String voivodeship){
        return ResponseEntity.ok(roadDifficultiesService.getResultsByVoivodeship(voivodeship));
    }

    @GetMapping("/map")
    ResponseEntity<Results> getResultsByMap(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Double distance){
        return ResponseEntity.ok(roadDifficultiesService.getResultsByDistance(latitude, longitude, distance));
    }

}
