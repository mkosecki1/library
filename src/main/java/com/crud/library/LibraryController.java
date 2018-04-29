package com.crud.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/ver1/library")
@RestController
public class LibraryController {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(method = RequestMethod.POST, value = "addBook",consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto){
        databaseService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBook")
    public void deleteBook(@RequestParam Long bookId){
        databaseService.deleteBook(bookId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBook")
    public BookDto getBook(@RequestParam Long bookId) throws BookLackException {
        return bookMapper.mapToBookDto(databaseService.findBook(bookId).orElseThrow(BookLackException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBooks")
    public List<BookDto> getBooks(){
        return bookMapper.mapToBookDtoList(databaseService.findAllBooks());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "modificationBook")
    public BookDto modificationBook(@RequestBody BookDto bookDto){
        return bookMapper.mapToBookDto(databaseService.saveBook(bookMapper.mapToBook(bookDto)));
    }
}
