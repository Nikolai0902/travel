package com.mydomain.travel.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class RouteDTO {

    private LocalDateTime data;
    private String pointA;
    private String pointB;
    private long travelTime;
}
