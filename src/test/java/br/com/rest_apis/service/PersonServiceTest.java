package br.com.rest_apis.service;

import br.com.rest_apis.dto.v1.PersonDto;
import br.com.rest_apis.model.Person;
import br.com.rest_apis.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class PersonServiceTest {

    @InjectMocks
    private PersonService service;

    @Mock
    PersonRepository repository;

    private Person person;
    private PersonDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startMocks();
    }

    @Test
    void findById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(person));

        PersonDto result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertTrue(result.toString().contains("[<http://localhost/api/person/v1/1>;rel=\"self\"]"));
        assertEquals("firstName",result.getFirstName());
        assertEquals("lastName",result.getLastName());
        assertEquals("address", result.getAddress());
        assertEquals("gender",result.getGender());

    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void createV2() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startMocks(){
        person = new Person(1L,"firstName","lastName", "address","gender");
        dto = new PersonDto(1L,"firstName","lastName", "address","gender");
    }


}