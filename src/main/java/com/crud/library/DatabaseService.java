package com.crud.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatabaseService {
    @Autowired
    private LibraryRepository repository;

    public Book saveBook(final Book book){
        return repository.save(book);
    }

    public void deleteBook(Long id){
        repository.deleteById(id);
    }

    public Optional<Book> findBook(Long id){
        return repository.findById(id);
    }

    public List<Book> findAllBooks(){
        return repository.findAll();
    }
}
