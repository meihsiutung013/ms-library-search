package com.company.microservice.service.implementation;

import com.company.microservice.model.Autor;
import com.company.microservice.model.AutorDto;
import com.company.microservice.repository.implementation.AutorRepository;
import com.company.microservice.request.CreateAutorRequest;
import com.company.microservice.service.interfaces.IAutorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
public class AutorService implements IAutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Autor> getAllAutores(String nombre) throws SQLException {
        if (StringUtils.hasLength(nombre)) {
            return autorRepository.search(nombre);
        }

        List<Autor> autores = autorRepository.getAllAutores();
        return autores.isEmpty() ? null : autores;
    }

    @Override
    public Autor getAutor(String id) throws SQLException {
        return autorRepository.getAutorById(Long.valueOf(id));
    }

    @Override
    public Autor insertAutor(CreateAutorRequest request) throws SQLException {
        if (request != null && StringUtils.hasLength(request.getNombre().trim())){
            Autor autor = Autor.builder().nombre(request.getNombre()).build();
            return autorRepository.save(autor);
        } else {
            return null;
        }
    }

    @Override
    public Autor updateAutor(String autorId, String updateRequest) throws SQLException {
        Autor autor = autorRepository.getAutorById(Long.valueOf(autorId));
        if (autor != null) {
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(updateRequest));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(autor)));
                Autor patched = objectMapper.treeToValue(target, Autor.class);
                autorRepository.save(patched);
                return patched;
            } catch (JsonProcessingException | JsonPatchException e) {
                log.error("Error updating autor {}", autorId, e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Autor updateAutor(String autorId, AutorDto updateRequest) throws SQLException {
        Autor autor = autorRepository.getAutorById(Long.valueOf(autorId));
        if (autor != null) {
            autor.update(updateRequest);
            autorRepository.save(autor);
            return autor;
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteAutor(String autorId) throws SQLException {

        Autor autor = autorRepository.getAutorById(Long.valueOf(autorId));

        if (autor != null) {
            autorRepository.delete(autor);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
