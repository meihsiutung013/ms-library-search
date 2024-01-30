package com.company.microservice.repository.interfaces;

import com.company.microservice.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IAutorJpaRepository extends JpaRepository<Autor, Long>, JpaSpecificationExecutor<Autor> {

    List<Autor> findByNombre(String name);
}
