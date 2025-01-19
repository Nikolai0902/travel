package com.mydomain.travel.service;

import com.mydomain.travel.model.Route;
import com.mydomain.travel.repository.RouteRepository;
import liquibase.repackaged.org.apache.commons.collections4.IteratorUtils;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;

    public Route save(Route route) {
        return routeRepository.save(route);
    }

    public Optional<Route> findById(UUID id) {
        return routeRepository.findById(id);
    }

    public boolean update(UUID id, Route newRoute) {
        return routeRepository.findById(id)
                .map(route-> {
                    newRoute.setId(route.getId());
                    routeRepository.save(newRoute);
                    return true;
                })
                .orElse(false);
    }

    public boolean delete(UUID id) {
        return routeRepository.findById(id)
                .map(route -> {
                    routeRepository.delete(route);
                    return true;
                })
                .orElse(false);
    }

    public List<Route> findAll() {
        var iterator = routeRepository.findAll().iterator();
        return IteratorUtils.toList(iterator);
    }
}
