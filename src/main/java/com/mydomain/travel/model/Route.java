package com.mydomain.travel.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity(name = "route")
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime data;
    @Column(name = "point_a")
    private String pointA;
    @Column(name = "point_b")
    private String pointB;
    @Column(name = "travel_time")
    private LocalTime travelTime;
}
