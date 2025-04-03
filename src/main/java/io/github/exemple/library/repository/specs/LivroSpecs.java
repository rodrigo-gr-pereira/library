package io.github.exemple.library.repository.specs;

import io.github.exemple.library.model.Livro;
import org.springframework.data.jpa.domain.Specification;

public class LivroSpecs {

    public static Specification<Livro> isbnEquals(String isbn){
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    public static Specification<Livro> tituloLike(String titulo){
        // upper(livro.titulo) like (%:param%)
        return (
                root,
                query,
                cb) -> cb.like( cb.upper(root.get("titulo")), "%" + titulo.toUpperCase() + "%");
    }

    public static Specification<Livro> generoEquals(String genero){
        return (root, query, cb) -> cb.equal(root.get("genero"), genero);
    }
}
