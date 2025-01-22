package com.mydomain.travel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mydomain.travel.dto.RouteDTO;
import com.mydomain.travel.persistent.model.Route;
import com.mydomain.travel.service.RouteService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;
    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Route route) {
        RouteDTO newRoute = routeService.save(route);
        return new ResponseEntity<>(newRoute, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return routeService.findById(id).map(
                 value -> new ResponseEntity<>(value, HttpStatus.OK)
        ).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UUID id, @RequestBody Route updatedRoute) {
        boolean updated = routeService.update(id, updatedRoute);
        return updated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        boolean updated = routeService.delete(id);
        return updated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(routeService.findAll(), HttpStatus.OK);
    }

    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            HttpMessageNotReadableException.class})
    public void exceptionHandlerBadRequest(Exception e, HttpServletResponse response) throws IOException {
        exceptionHandler(response, HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(value = { DataIntegrityViolationException.class})
    public void exceptionHandlerIntServErr(Exception e, HttpServletResponse response) throws IOException {
        exceptionHandler(response, HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    private void exceptionHandler(
            HttpServletResponse response,
            HttpStatus httpStatus,
            Exception e) throws IOException {
        response.setStatus(httpStatus.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", e.getMessage());
                put("type", e.getClass());
            }
        }));
    }
}
