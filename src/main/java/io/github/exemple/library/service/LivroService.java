package io.github.exemple.library.service;


import io.github.exemple.library.model.GeneroLivro;
import io.github.exemple.library.model.Livro;
import io.github.exemple.library.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.exemple.library.repository.specs.LivroSpecs.*;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    public Livro salvar(Livro livro) {
        return  repository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Livro livro){
        repository.delete(livro);
    }

    //isbn, titulo, nome autor, genero, ano de publicacao
    public List<Livro> pesquisa(
            String isbn, String titulo, String nomeAutor, GeneroLivro genero, Integer anoPublicacao){

        // select * from livro where isbn = :isbn and nomeAutor =
     //   Specification<Livro> specs = Specification
       //         .where(LivroSpecs.isbnEquals(isbn))
     //           .and(LivroSpecs.tituloLike(titulo))
       //         .and(LivroSpecs.generoEquals(genero))
         //       ;

        // select * from livro where 0 = 0
        Specification<Livro> specs = Specification
                .where((root, query, cb) -> cb.conjunction());

        if(isbn != null){
            // query = query and isbn = :isbn
            specs = specs.and(isbnEquals(isbn));
        }

        if(titulo != null){
            specs = specs.and(tituloLike(titulo));
        }

        if(genero != null){
            specs = specs.and(generoEqual(genero));
        }

        if(anoPublicacao != null){
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }


        return repository.findAll(specs);
    }
}

