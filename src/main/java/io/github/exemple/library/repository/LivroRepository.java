package io.github.exemple.library.repository;

import io.github.exemple.library.model.Autor;
import io.github.exemple.library.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //Query method
    //Select * from livro where id_autor = id
    List<Livro> findByAutor(Autor autor);

    //Select *from livro where titulo = titulos
    List<Livro> findByTitulo(String titulo);

    //Select *from livro where ISBN
    List<Livro> findByIsdn(String isdn);

    //Select * from livro where titulo = ? and preco = ?
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    //Selecot * from libro where titulo = ? or Isdn = ?
    List<Livro> findByTituloOrIsdn(String titulo, String isdn);

}