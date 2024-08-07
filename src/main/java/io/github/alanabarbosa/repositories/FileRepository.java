package io.github.alanabarbosa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.alanabarbosa.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findByFilename(String filename);
}