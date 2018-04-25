package com.crud.library;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("ver1/library")
@RestController
public class LibraryController {
    @RequestMapping(method = RequestMethod.POST, value = "addBook")
    public void addBook(){
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBook")
    public void deleteBook(){
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBook")
    public BookDto getBook(){
        return null;
    }

    @RequestMapping()
    public List<BookDto> getBooks(){
        return null;
    }

    @RequestMapping()
    public BookDto modificationBook(@RequestBody BookDto bookId){
        return null;
    }



}
