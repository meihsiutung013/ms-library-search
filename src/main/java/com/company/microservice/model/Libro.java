package com.company.microservice.model;

import lombok.Data;

@Data
public class Libro {
    private int libId;
    private int autId;
    private int catId;
    private String libNombre;
    private double libPrecioAlquiler;
    private String libAnioPublicacion;
    private String libISBN;
}
