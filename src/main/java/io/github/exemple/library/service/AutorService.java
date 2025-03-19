package io.github.exemple.library.service;

import io.github.exemple.library.model.Autor;
import io.github.exemple.library.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository){
        this.repository = repository;
    }

    public Autor salvar(Autor autor){
        return  repository.save(autor);
    }
}
