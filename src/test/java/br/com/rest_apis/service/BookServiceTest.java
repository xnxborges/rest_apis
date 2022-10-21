package br.com.rest_apis.service;

import br.com.rest_apis.dto.v1.BookDto;
import br.com.rest_apis.exceptions.RequiredObjectIsNullException;
import br.com.rest_apis.model.Book;
import br.com.rest_apis.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static br.com.rest_apis.helper.MockBook.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
class BookServiceTest {

    @InjectMocks
    private BookService service;

    @Mock
    private BookRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Book book = getMockEntity(1);

        when(repository.findById(1L)).thenReturn(Optional.of(book));

        BookDto result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertTrue(result.toString().contains("[<http://localhost/api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Name Test1",result.getAuthor());
        assertNotNull(book.getDate());
        assertEquals("Tittle Test1", result.getTitle());
        assertEquals(25D, result.getPrice());

    }


    @Test
    void findAll() {
        List<Book> book = getMockEntityList();

        when(repository.findAll()).thenReturn(book);

        List<BookDto> result = service.findAll();

        assertNotNull(result);
        assertEquals(14, result.size());

        BookDto bookOne = result.get(1);
        assertNotNull(bookOne);
        assertNotNull(bookOne.getId());
        assertNotNull(bookOne.getLinks());

        assertEquals("Author Name Test1",bookOne.getAuthor());
        assertNotNull(bookOne.getDate());
        assertEquals("Tittle Test1", bookOne.getTitle());
        assertEquals(25D, bookOne.getPrice());

        BookDto bookFour = result.get(4);
        assertNotNull(bookFour);
        assertNotNull(bookFour.getId());
        assertNotNull(bookFour.getLinks());

        assertEquals("Author Name Test4",bookFour.getAuthor());
        assertNotNull(bookFour.getDate());
        assertEquals("Tittle Test4", bookFour.getTitle());
        assertEquals(25D, bookFour.getPrice());

        BookDto bookTwelve = result.get(12);
        assertNotNull(bookTwelve);
        assertNotNull(bookTwelve.getId());
        assertNotNull(bookTwelve.getLinks());

        assertEquals("Author Name Test12",bookTwelve.getAuthor());
        assertNotNull(bookTwelve.getDate());
        assertEquals("Tittle Test12", bookTwelve.getTitle());
        assertEquals(25D,bookTwelve.getPrice());

    }

    @Test
    void create() {
        Book book = getMockEntity(1);
        book.setId(1L);

        BookDto bookDto = getMockDto(1);
        bookDto.setId(1L);

        when(repository.save(book)).thenReturn(book);

        BookDto result = service.create(bookDto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertEquals("Author Name Test1",result.getAuthor());
        assertNotNull(book.getDate());
        assertEquals("Tittle Test1", result.getTitle());
        assertEquals(25D, result.getPrice());

    }
    @Test
    void createWithNullBook() {

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void update() {
        Book book = getMockEntity(1);
        book.setId(1L);

        book.setId(1L);

        BookDto bookDto = getMockDto(1);
        bookDto.getId();

        when(repository.findById(1L)).thenReturn(Optional.of(book));
        when(repository.save(book)).thenReturn(book);

        BookDto result = service.update(bookDto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertEquals("Author Name Test1",result.getAuthor());
        assertNotNull(book.getDate());
        assertEquals("Tittle Test1", result.getTitle());
        assertEquals(25D,result.getPrice());
    }

    @Test
    void updateWithNullBook() {

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void delete() {
        Book book = getMockEntity(1);
        book.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(book));
        doNothing().when(repository).delete(book);
        service.delete(1L);

        verify(repository, times(1)).delete(book);

    }

}