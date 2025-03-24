package io.github.exemple.library.controller.dto;

import io.github.exemple.library.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 2, max = 100, message = "Campo fora do tamanho padrão")
        String nome,
        @NotNull(message = "Campo obrigatório")
        @Past(message = "não pode ser uma data futura")
        LocalDate dataNascimento,
        @NotBlank(message = "Campo obrigatório")
        @Size(max = 50, min = 2, message = "Campo fora do tamanho padrão")
        String nacionalidade) {

    public Autor mapearParaAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }


}
