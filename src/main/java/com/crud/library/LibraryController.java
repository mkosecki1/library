package com.crud.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("ver1/library")
@RestController
public class LibraryController {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(method = RequestMethod.POST, value = "addBook",consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto){

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBook")
    public void deleteBook(){
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBook")
    public BookDto getBook(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBooks")
    public List<BookDto> getBooks(){
        return bookMapper.mapToBookDtoList(databaseService.findAllBooks());
    }

    @RequestMapping()
    public BookDto modificationBook(@RequestBody BookDto bookId){
        return null;
    }



}
