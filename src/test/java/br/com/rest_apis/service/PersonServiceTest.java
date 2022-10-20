package br.com.rest_apis.service;

import br.com.rest_apis.dto.v1.PersonDto;
import br.com.rest_apis.exceptions.RequiredObjectIsNullException;
import br.com.rest_apis.model.Person;
import br.com.rest_apis.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static br.com.rest_apis.helper.MockPerson.getMockDto;
import static br.com.rest_apis.helper.MockPerson.getMockEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonServiceTest {

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person person = getMockEntity(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        PersonDto result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertTrue(result.toString().contains("[<http://localhost/api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female",result.getGender());

    }


    @Test
    void findAll() {
    }

    @Test
    void create() {
        Person person = getMockEntity(1);
        person.setId(1L);

        PersonDto personDto = getMockDto(1);
        personDto.setKey(1L);

        when(repository.save(person)).thenReturn(person);

        PersonDto result = service.create(personDto);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female",result.getGender());

    }
    @Test
    void createWithNullPerson() {

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void update() {
        Person person = getMockEntity(1);
        person.setId(1L);

        person.setId(1L);

        PersonDto personDto = getMockDto(1);
        personDto.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(person);

        PersonDto result = service.update(personDto);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        System.out.println(result);
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertEquals("Address Test1", result.getAddress());
        assertEquals("Female",result.getGender());
    }

    @Test
    void updateWithNullPerson() {

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void delete() {
        Person person = getMockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        doNothing().when(repository).delete(person);
        service.delete(1L);

        verify(repository, times(1)).delete(person);

    }

}