package io.github.exemple.library.service;

import io.github.exemple.library.model.Autor;
import io.github.exemple.library.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository){
        this.repository = repository;
    }

    public Autor salvar(Autor autor){
        return  repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Autor autor){
        repository.delete(autor);
    }

    public Autor atualizar(Autor autor){
        if (autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar e necessario que o autor já esteja na base");
        }
        return  repository.save(autor);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade){
        if (nome != null && nacionalidade != null){
           return repository.findByNomeAndNacionalidade(nome, nacionalidade);
        }
        if(nome != null){
           return repository.findByNome(nome);
        }
        if (nacionalidade != null){
           return repository.findByNacionalidade(nacionalidade);
        }

            return repository.findAll();
    }
}
