package io.github.exemple.library.controller;

import io.github.exemple.library.controller.dto.AutorDTO;
import io.github.exemple.library.controller.dto.ErroResposta;
import io.github.exemple.library.controller.mappers.AutorMapper;
import io.github.exemple.library.exceptions.OperacaoNaopermitidaException;
import io.github.exemple.library.exceptions.RegistroDuplicadoException;
import io.github.exemple.library.model.Autor;
import io.github.exemple.library.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
// https://localhost:8080/autores
public class AutorController implements GenericController {

    private final AutorService autorService;
    private final AutorMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid AutorDTO dto) {

        Autor autor = mapper.toEntity(dto);
        autorService.salvar(autor);
        // https://localhost:8080/autores/5644wr4tt-ouygf-hgtrdgh
        URI location = geraHeaderLocation(autor.getId());
//        return  new ResponseEntity("Autor salvo com sucesso!" + autor, HttpStatus.CREATED);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);

        return autorService
                .obterPorId(idAutor)
                .map(autor -> {
                    AutorDTO dto = mapper.toDTO(autor);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Optional<Autor> autorOptional = autorService.obterPorId(idAutor);
    // if (autorOptional.isPresent()) {
    //   Autor autor = autorOptional.get();
    //  AutorDTO dto = mapper.toDTO(autor);
    //  return ResponseEntity.ok(dto);
    //      }
    //return ResponseEntity.notFound().build();
    //}

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        autorService.deletar(autorOptional.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade) {
        List<Autor> resultado = autorService.pesquisaByExample(nome, nacionalidade);
        List<AutorDTO> lista = resultado
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        //autor -> new AutorDTO(
        //autor.getId(),
        //autor.getNome(),
        //autor.getDataNascimento(),
        //autor.getNacionalidade())
        // ).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable("id") String id, @RequestBody AutorDTO dto) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var autor = autorOptional.get();
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        autor.setDataNascimento(dto.dataNascimento());

        autorService.atualizar(autor);

        return ResponseEntity.noContent().build();
    }
}