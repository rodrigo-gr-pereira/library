package io.github.exemple.library.repository;

import io.github.exemple.library.model.Autor;
import io.github.exemple.library.model.GeneroLivro;
import io.github.exemple.library.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsdn("98807-9090");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("OFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository
                .findById(UUID.fromString("4b1bb2bb-6e8e-4ba1-83e5-c744f3ef29ef"))
                .orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }
}