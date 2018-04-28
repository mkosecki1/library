package com.crud.library;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LibraryRepository extends CrudRepository<Book,Long> {
    @Override
    Book save(Book book);

    @Override
    void deleteById(Long id);

    @Override
    Optional<Book> findById(Long id);

    @Override
    List<Book> findAll();
}
