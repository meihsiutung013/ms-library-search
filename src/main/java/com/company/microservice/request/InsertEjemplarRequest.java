package com.company.microservice.request;

import lombok.Data;

@Data

public class InsertEjemplarRequest {
    private int libroId;
    private boolean estado;

}
