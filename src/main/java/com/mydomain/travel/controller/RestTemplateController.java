package com.mydomain.travel.controller;

import com.mydomain.travel.dto.RouteDTO;
import com.mydomain.travel.persistent.model.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@RequestMapping("/rest-template")
@RequiredArgsConstructor
public class RestTemplateController {

    @Value("${server.url}")
    private String url;

    private final RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Route route) {
        return restTemplate.postForEntity(url, route, RouteDTO.class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return restTemplate.getForEntity(url + "/" + id, RouteDTO.class);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Route updatedRoute) {
        restTemplate.put(url, updatedRoute);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        restTemplate.delete(url + "/" + id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        ResponseEntity<RouteDTO[]> response = restTemplate.getForEntity(url + "/all", RouteDTO[].class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
