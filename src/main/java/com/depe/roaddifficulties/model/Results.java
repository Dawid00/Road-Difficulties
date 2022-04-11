package com.depe.roaddifficulties.model;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Results {
    private LocalDateTime date;
    private List<TrafficDifficulty> trafficDifficulties;
}
