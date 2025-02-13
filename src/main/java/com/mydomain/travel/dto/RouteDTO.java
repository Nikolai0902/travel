package com.mydomain.travel.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class RouteDTO {

    private UUID id;
    private LocalDateTime data;
    private String pointA;
    private String pointB;
    private long travelTime;
}
