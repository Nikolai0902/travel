package com.mydomain.travel.service;

import com.mydomain.travel.dto.RouteDTO;
import com.mydomain.travel.mapper.RouteDTOMapper;
import com.mydomain.travel.persistent.model.Route;
import com.mydomain.travel.persistent.repository.RouteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final RouteDTOMapper mapper;

    public RouteService(RouteRepository routeRepository, RouteDTOMapper mapper) {
        this.routeRepository = routeRepository;
        this.mapper = mapper;
    }

    public RouteDTO save(Route route) {
        return mapper.ToDto(routeRepository.save(route));
    }

    public Optional<RouteDTO> findById(UUID id) {
        return routeRepository.findById(id).map(mapper::ToDto);
    }

    @Transactional
    public boolean update(Route newRoute) {
        return Optional.ofNullable(newRoute.getId())
                .flatMap(routeRepository::findById)
                .map(route -> {
                    route.setData(newRoute.getData());
                    route.setPointA(newRoute.getPointA());
                    route.setPointB(newRoute.getPointB());
                    route.setTravelTime(newRoute.getTravelTime());
                    routeRepository.save(route);
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

    public List<RouteDTO> findAll() {
        return routeRepository.findAll()
                .stream()
                .map(mapper::ToDto)
                .collect(Collectors.toList());
    }
}
