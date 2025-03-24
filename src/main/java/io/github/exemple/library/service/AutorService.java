package io.github.exemple.library.service;

import io.github.exemple.library.exceptions.OperacaoNaopermitidaException;
import io.github.exemple.library.model.Autor;
import io.github.exemple.library.repository.AutorRepository;
import io.github.exemple.library.repository.LivroRepository;
import io.github.exemple.library.validator.AutorValidador;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository repository;
    private final AutorValidador validador;
    private final LivroRepository livroRepository;

    public Autor salvar(Autor autor){
        validador.validar(autor);
        return  repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Autor autor){
        if (possuiLivro(autor)){
           throw new OperacaoNaopermitidaException("Não é permitido excluir um autpr que possui livros cadastrados");
        }
        repository.delete(autor);
    }

    public Autor atualizar(Autor autor){
        if (autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar e necessario que o autor já esteja na base");
        }
        validador.validar(autor);
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

    public List<Autor> pesquisaByExample(String nome, String nacionalidade){
        var autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnorePaths("id", "dataNascimento", "dataCadastro")
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Autor> autorExample = Example.of(autor, matcher);
        return repository.findAll(autorExample);
    }

    public boolean possuiLivro(Autor autor){
        return livroRepository.existsByAutor(autor);
    }
}
