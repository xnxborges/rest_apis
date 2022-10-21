package br.com.rest_apis.service;

import br.com.rest_apis.controller.BookController;
import br.com.rest_apis.dto.v1.BookDto;
import br.com.rest_apis.exceptions.RequiredObjectIsNullException;
import br.com.rest_apis.exceptions.ResourceNotFoundException;
import br.com.rest_apis.mapper.DozerMapper;
import br.com.rest_apis.model.Book;
import br.com.rest_apis.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class BookService  {
    private final Logger logger = Logger.getLogger(BookService.class.getName());
    public static final String NO_RECORDS = "No records found for this id";

    @Autowired
    BookRepository repository;

    public BookDto findById(Long id){
        logger.info("Finding one book");

        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NO_RECORDS));

        BookDto bookDto = DozerMapper.parseObject(book, BookDto.class);
        bookDto.add(linkTo(methodOn(BookController.class).findById(bookDto.getId())).withSelfRel());
        return bookDto;
    }

    public List<BookDto> findAll(){
        logger.info("Finding all books");

        List<BookDto> bookDto = DozerMapper.parseListObjects(repository.findAll(), BookDto.class);
        bookDto.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getId())).withSelfRel()));
        return bookDto;
    }


    public BookDto create(BookDto bookDto){
        if (bookDto == null) throw new RequiredObjectIsNullException();
        logger.info("Creating one books");

        Book book = DozerMapper.parseObject(bookDto, Book.class);

        BookDto dto = DozerMapper.parseObject(repository.save(book), BookDto.class);
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel());
        return dto;
    }

    public BookDto update(BookDto bookDto){
        if (bookDto == null) throw new RequiredObjectIsNullException();
        logger.info("Updating one books");
        Book entity = repository.findById(bookDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(NO_RECORDS));

        entity.setAuthor(bookDto.getAuthor());
        entity.setDate(bookDto.getDate());
        entity.setTitle(bookDto.getTitle());
        entity.setPrice(bookDto.getPrice());

        BookDto dto = DozerMapper.parseObject(repository.save(entity), BookDto.class);
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel());

        return bookDto;
    }

    public void delete(Long id){
        logger.info("Deleting one book");

        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NO_RECORDS));

        repository.delete(book);

    }
}
