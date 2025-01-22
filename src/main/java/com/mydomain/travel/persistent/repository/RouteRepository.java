package com.mydomain.travel.persistent.repository;

import com.mydomain.travel.persistent.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RouteRepository extends JpaRepository<Route, UUID> {
}
