package com.crud.library;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BookMapperTest {
    @Test
    public void mapToBook() throws Exception {
        //Given
        BookDto bookDto = new BookDto(1L,"New_Book","New_Author");
        BookMapper bookMapper = new BookMapper();
        //When
        Book book = bookMapper.mapToBook(bookDto);
        //Then
        Assert.assertNotNull(book.getId());
        Assert.assertEquals("New_Book",book.getTitle());
        Assert.assertEquals("New_Author",book.getAuthor());

    }

    @Test
    public void mapToBookDto() throws Exception {
        //Given
        Book book = new Book(1L,"New_Book","New_Author");
        BookMapper bookMapper = new BookMapper();
        //When
        BookDto bookDto = bookMapper.mapToBookDto(book);
        //Then
        Assert.assertNotNull(bookDto.getId());
        Assert.assertEquals("New_Book",bookDto.getTitle());
        Assert.assertEquals("New_Author",bookDto.getAuthor());
    }

    @Test
    public void mapToBookDtoList() throws Exception {
        //Given
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1L,"New_Book","New_Author"));
        //When
        BookMapper taskMapper = new BookMapper();
        List<BookDto> bookDtoList = taskMapper.mapToBookDtoList(bookList);
        //Then
        Assert.assertNotNull(bookDtoList.get(0).getId());
        Assert.assertEquals("New_Book",bookDtoList.get(0).getTitle());
        Assert.assertEquals("New_Author",bookDtoList.get(0).getAuthor());
    }
}