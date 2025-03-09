package io.github.exemple.library.repository;

import io.github.exemple.library.model.Autor;
import io.github.exemple.library.model.GeneroLivro;
import io.github.exemple.library.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1977, 12, 31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("04407cf6-6619-4809-bef4-b74578ee6301");

        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()){

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("d9bf4c9d-3451-4fc7-aafa-e80efae3ac61");
        repository.deleteById(id);
    }

 //  @Test
 //public void deleteTest(){
  //  var id = UUID.fromString("9300d739-3c70-4755-ae76-a3e66bb7a378");
    // var Maria = repository.findById(id).get();
    // repository.deleteById(Maria);

//    }
    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Rodrigo");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1987, 8 , 9));
        autor.setAutor(autor);


        Livro livro = new Livro();
        livro.setIsdn("98806-6184");
        livro.setPreco(BigDecimal.valueOf(200));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("A volta dos de não foram");
        livro.setDataPublicacao(LocalDate.of(1999, 3, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsdn("8987-0909");
        livro2.setPreco(BigDecimal.valueOf(150));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("Vamos fugir");
        livro2.setDataPublicacao(LocalDate.of(1980, 1, 2));
        livro2.setAutor(autor);
        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

    //    livroRepository.saveAll(autor.getLivros());
    }

    @Test
    //@Transactional
    void listarLivrosAutor(){
        var id = UUID.fromString("ee932dda-3ed0-42e7-af7d-dffdd9cd07a3");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }
}



