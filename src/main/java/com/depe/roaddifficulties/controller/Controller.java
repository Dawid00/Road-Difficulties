package com.depe.roaddifficulties.controller;


import com.depe.roaddifficulties.model.Results;
import com.depe.roaddifficulties.model.Voivodeship;
import com.depe.roaddifficulties.service.RoadDifficultiesService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/traffic-difficulties")
class Controller {

    private final RoadDifficultiesService roadDifficultiesService;

    Controller(RoadDifficultiesService roadDifficultiesService) {
        this.roadDifficultiesService = roadDifficultiesService;
    }


    @GetMapping()
    Results getResults(){
        return roadDifficultiesService.getResults();
    }

    @GetMapping("/road/{road}")
    Results getResultsByWojewodztwo(@PathVariable String road){
        return roadDifficultiesService.getResultsByRoad(road);
    }

    @GetMapping("/voivodeship/{voivodeship}")
    Results getResultsByWojewodztwo(@PathVariable Voivodeship voivodeship){
        return roadDifficultiesService.getResultsByVoivodeship(voivodeship);
    }

    @GetMapping("/map")
    Results getResultsByMap(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Double distance){
        return roadDifficultiesService.getResultsByDistance(latitude, longitude, distance);
    }

}
