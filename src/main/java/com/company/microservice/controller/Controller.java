package com.company.microservice.controller;

import com.company.microservice.model.Autor;
import com.company.microservice.service.interfaces.IAutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/autor")
public class Controller {
    private final IAutorService autorService;

    @Autowired
    public Controller(IAutorService autorService) {
        this.autorService = autorService;
    }

}
