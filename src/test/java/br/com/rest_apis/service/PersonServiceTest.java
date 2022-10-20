package br.com.rest_apis.service;

import br.com.rest_apis.dto.v1.PersonDto;
import br.com.rest_apis.helper.MockPerson;
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

import static br.com.rest_apis.helper.MockPerson.getMockEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class PersonServiceTest {

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    private MockPerson mockPerson;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person person = getMockEntity(1);

        when(repository.findById(anyLong())).thenReturn(Optional.of(person));

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

}