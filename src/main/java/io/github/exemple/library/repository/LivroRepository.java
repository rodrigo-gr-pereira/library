package io.github.exemple.library.repository;

import io.github.exemple.library.model.Autor;
import io.github.exemple.library.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see LivroRepositoryTest
 */

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

    //Select * from livro where data_publicacao between ? and ?
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    // JPQL -> referencias as entidades e as propriedades
    // select l.* from livro as l order by l.titulo
    @Query(" select l from Livro as l order by l.titulo, l.preco")
    List<Livro> listarTodosOrdenadoPorTituloAndPreco();

    /**
     * select a.*
     * from livro l
     * join autor a on a.id  = l.id_autor
     */
    @Query(" select a from Livro l join l.autor a ")
    List<Autor> listarAutoresDoslivros();

    //select distinct l.* from livro l
    @Query(" select distinct l.titulo from Livro l ")
    List<String> listarNomesDiferenteslivros();

    @Query("""
            select l.genero
            from Livro l
            join l.autor a
            where a.nacionalidade = 'Brasileira'
            order by l.generos
            """)
    List<String> listarGenerosDeAutoresBrasileiros();
}