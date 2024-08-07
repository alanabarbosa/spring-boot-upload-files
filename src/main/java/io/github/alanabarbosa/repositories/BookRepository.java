package io.github.alanabarbosa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.alanabarbosa.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
