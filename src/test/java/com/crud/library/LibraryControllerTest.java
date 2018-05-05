package com.crud.library;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LibraryController.class)
public class LibraryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DatabaseService databaseService;

    @MockBean
    private BookMapper bookMapper;
    private Book book;
    private BookDto bookDto;

    @Before
    public void createObject(){
        book = new Book(1L,"New_Book","New_Author");
        bookDto = new BookDto(1L,"New_Book","New_Author");
    }

    @Test
    public void addBookTest() throws Exception {
        //Given
        when(databaseService.saveBook(book)).thenReturn(book);
        when(bookMapper.mapToBookDto(book)).thenReturn(bookDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookDto);
        //When & Then
        mockMvc.perform(post("/ver1/library/addBook").contentType(MediaType.APPLICATION_JSON)
                .param("bookId", "1")
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteBookTest() throws Exception {
        //Given
        doNothing().when(databaseService).deleteBook(book.getId());
        //When & Then
        mockMvc.perform(delete("/ver1/library/deleteBook").contentType(MediaType.APPLICATION_JSON)
                .param("bookId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getBookTest() throws Exception {
        //Given
        when(databaseService.findBook(book.getId())).thenReturn(Optional.ofNullable(book));
        when(bookMapper.mapToBookDto(book)).thenReturn(bookDto);
        //When & Then
        mockMvc.perform(get("/ver1/library/getBook").contentType(MediaType.APPLICATION_JSON)
                .param("bookId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("New_Book")))
                .andExpect(jsonPath("$.author", is("New_Author")));
    }

    @Test
    public void getBooksTest() throws Exception {
        //Given
        List<BookDto> bookDtoList = new ArrayList<>();
        bookDtoList.add(bookDto);
        bookDtoList.add(new BookDto(2L,"New_Book_2","New_Author_2"));
        when(bookMapper.mapToBookDtoList(databaseService.findAllBooks())).thenReturn(bookDtoList);
        //When & Then
        mockMvc.perform(get("/ver1/library/getBooks").contentType(MediaType.APPLICATION_JSON)
                .param("bookId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("New_Book")))
                .andExpect(jsonPath("$[0].author", is("New_Author")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("New_Book_2")))
                .andExpect(jsonPath("$[1].author", is("New_Author_2")));
    }

    @Test
    public void modificationBookTest() throws Exception {
        when(databaseService.saveBook(book)).thenReturn(book);
        when(bookMapper.mapToBookDto(book)).thenReturn(bookDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookDto);
        //When & Then
        mockMvc.perform(put("/ver1/library/modificationBook").contentType(MediaType.APPLICATION_JSON)
                .param("bookId", "1")
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}