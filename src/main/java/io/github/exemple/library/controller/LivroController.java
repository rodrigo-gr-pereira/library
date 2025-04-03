package io.github.exemple.library.controller;

import io.github.exemple.library.controller.dto.AutorDTO;
import io.github.exemple.library.controller.dto.CadastroLivroDTO;
import io.github.exemple.library.controller.dto.ErroResposta;
import io.github.exemple.library.controller.dto.ResultadoPesquisaLivroDTO;
import io.github.exemple.library.controller.mappers.LivroMapper;
import io.github.exemple.library.exceptions.RegistroDuplicadoException;
import io.github.exemple.library.model.Autor;
import io.github.exemple.library.model.Livro;
import io.github.exemple.library.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto) {

        Livro livro = mapper.toEntity(dto);
        //mapear dto para entidade
        service.salvar(livro);
        // enviar a entidade para serviço validar e salvar base
        var url = geraHeaderLocation(livro.getId());
        //criar URL para acesso dos dados dos livros
        return ResponseEntity.created(url).build();
        //retornar o codigo created com header location
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(
            @PathVariable("id") String id){
        return service.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    var dto = mapper.toDTO(livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") String id){
        return service.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    service.deletar(livro);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
