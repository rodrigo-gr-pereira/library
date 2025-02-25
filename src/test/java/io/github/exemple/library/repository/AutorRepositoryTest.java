package io.github.exemple.library.repository;

import io.github.exemple.library.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

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
}

