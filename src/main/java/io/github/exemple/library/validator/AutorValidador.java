package io.github.exemple.library.validator;

import io.github.exemple.library.exceptions.RegistroDuplicadoException;
import io.github.exemple.library.model.Autor;
import io.github.exemple.library.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class AutorValidador {

    private AutorRepository repository;

    public AutorValidador(AutorRepository repository) {
        this.repository = repository;
    }

    public void validar(Autor autor) {
        if (existeAutorCadastrado(autor)) {
            throw new RegistroDuplicadoException("Autor já cadastrado!");
        }
    }

    private boolean existeAutorCadastrado(Autor autor){
        Optional<Autor> autorEncontrado = repository.findByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade()
        );

        if(autor.getId() == null){
            return autorEncontrado.isPresent();
        }

        return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();
    }
}