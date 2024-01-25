// EjemplarService.java
package com.company.microservice.service.implementation;

import com.company.microservice.model.Ejemplar;
import com.company.microservice.repository.interfaces.IEjemplarRepository;
import com.company.microservice.service.interfaces.IEjemplarService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EjemplarService implements IEjemplarService {
    private final IEjemplarRepository ejemplarRepository;

    public EjemplarService(IEjemplarRepository ejemplarRepository) {
        this.ejemplarRepository = ejemplarRepository;
    }

    @Override
    public List<Ejemplar> getAllEjemplares() throws SQLException {
        return this.ejemplarRepository.getAllEjemplares();
    }

    @Override
    public void insertEjemplarProcedure(int libroId, boolean estado) throws SQLException {
        ejemplarRepository.insertEjemplarProcedure(libroId, estado);
    }

    @Override
    public void updateEjemplarProcedure(int ejemId, int libroId, boolean estado) throws SQLException {
        ejemplarRepository.updateEjemplarProcedure(ejemId, libroId, estado);
    }
/*
    @Override
    public Ejemplar getEjemplarById(int id) throws SQLException {
        // Implementación de getEjemplarById
    }



    @Override
    public void updateEjemplar(Ejemplar ejemplar) throws SQLException {
        // Implementación de updateEjemplar
    }

    @Override
    public void deleteEjemplar(int id) throws SQLException {
        // Implementación de deleteEjemplar
    }
*/
    // Otros métodos específicos para Ejemplar, si es necesario
}
