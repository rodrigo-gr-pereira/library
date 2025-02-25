package io.github.exemple.library.repository;

import io.github.exemple.library.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
