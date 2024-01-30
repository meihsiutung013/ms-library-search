package com.company.microservice.repository.implementation;

import com.company.microservice.data.util.SearchCriteria;
import com.company.microservice.data.util.SearchOperation;
import com.company.microservice.data.util.SearchStatement;
import com.company.microservice.model.Autor;
import com.company.microservice.repository.interfaces.IAutorJpaRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AutorRepository {

    private final IAutorJpaRepository repository;

    public List<Autor> getAllAutores() {
        return repository.findAll();
    }

    public Autor getAutorById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Autor save(Autor autor) {
        return repository.save(autor);
    }

    public void delete(Autor autor) {
        repository.delete(autor);
    }

    public List<Autor> search(String nombre) {
        SearchCriteria<Autor> spec = new SearchCriteria<>();
        if (StringUtils.isNotBlank(nombre)) {
            spec.add(new SearchStatement("AUT_NOMBRE", nombre, SearchOperation.MATCH));
        }
        return repository.findAll(spec);
    }
}
