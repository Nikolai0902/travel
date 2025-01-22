package com.mydomain.travel.persistent.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "route")
@RequiredArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "data")
    private LocalDateTime data;
    @Column(name = "point_a")
    private String pointA;
    @Column(name = "point_b")
    private String pointB;
    @Column(name = "travel_time")
    private long travelTime;
}
