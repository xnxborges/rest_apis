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

import java.util.List;
import java.util.Optional;

import static br.com.rest_apis.helper.MockPerson.*;
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
        List<Person> person = getMockEntityList();

        when(repository.findAll()).thenReturn(person);

        List<PersonDto> result = service.findAll();

        assertNotNull(result);
        assertEquals(14, result.size());

        PersonDto personOne = result.get(1);
        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());

        assertEquals("First Name Test1",personOne.getFirstName());
        assertEquals("Last Name Test1",personOne.getLastName());
        assertEquals("Address Test1", personOne.getAddress());
        assertEquals("Female",personOne.getGender());

        PersonDto personFour = result.get(4);
        assertNotNull(personFour);
        assertNotNull(personFour.getKey());
        assertNotNull(personFour.getLinks());

        assertEquals("First Name Test4",personFour.getFirstName());
        assertEquals("Last Name Test4",personFour.getLastName());
        assertEquals("Address Test4", personFour.getAddress());
        assertEquals("Male",personFour.getGender());

        PersonDto personTwelve = result.get(12);
        assertNotNull(personTwelve);
        assertNotNull(personTwelve.getKey());
        assertNotNull(personTwelve.getLinks());

        assertEquals("First Name Test12",personTwelve.getFirstName());
        assertEquals("Last Name Test12",personTwelve.getLastName());
        assertEquals("Address Test12", personTwelve.getAddress());
        assertEquals("Male",personTwelve.getGender());

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