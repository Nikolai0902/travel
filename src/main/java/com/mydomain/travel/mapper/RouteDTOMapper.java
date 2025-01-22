package com.mydomain.travel.mapper;

import com.mydomain.travel.dto.RouteDTO;
import com.mydomain.travel.persistent.model.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteDTOMapper {
    RouteDTO ToDto(Route source);
    Route ToRoute(RouteDTO routeDTO);
}
