package io.github.exemple.library.controller.mappers;

import io.github.exemple.library.controller.dto.CadastroLivroDTO;
import io.github.exemple.library.controller.dto.ResultadoPesquisaLivroDTO;
import io.github.exemple.library.model.GeneroLivro;
import io.github.exemple.library.model.Livro;
import io.github.exemple.library.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null) )")
    @Mapping(target = "genero", expression = "java( mapGenero(dto.genero()) )")
    public abstract Livro toEntity(CadastroLivroDTO dto);

    public abstract ResultadoPesquisaLivroDTO toDTO(Livro livro);
    protected GeneroLivro mapGenero(String genero) {
        if (genero == null || genero.isBlank()) return null;
        return GeneroLivro.valueOf(genero.toUpperCase());
    }
}
