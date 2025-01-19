package com.mydomain.travel.repository;

import com.mydomain.travel.model.Route;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RouteRepository extends CrudRepository<Route, UUID> {
}
