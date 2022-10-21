package br.com.rest_apis.helper;

import br.com.rest_apis.dto.v1.BookDto;
import br.com.rest_apis.model.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {

    public static Book getMockEntity() {
        return getMockEntity(0);
    }

    public static BookDto getMockDto() {
        return getMockDto(0);
    }

    public static List<Book> getMockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(getMockEntity(i));
        }
        return books;
    }

    public static List<BookDto> getMockDtoList() {
        List<BookDto> booksDto = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            booksDto.add(getMockDto(i));
        }
        return booksDto;
    }

    public static Book getMockEntity(Integer number) {
        Book book = new Book();
        book.setId(number.longValue());
        book.setAuthor("Author Name Test" + number);
        book.setDate(new Date());
        book.setTitle("Tittle Test" + number);
        book.setPrice(25D);
        return book;
    }

    public static BookDto getMockDto(Integer number) {
        BookDto bookDto = new BookDto();
        bookDto.setId(number.longValue());
        bookDto.setAuthor("Author Name Test" + number);
        bookDto.setDate(new Date());
        bookDto.setTitle("Tittle Test" + number);
        bookDto.setPrice(25D);
        return bookDto;
    }

}
