package br.com.rest_apis.service;

import br.com.rest_apis.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id){
        logger.info("Finding one person");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Natan");
        person.setLastName("Borges");
        person.setAddress("Cotia - São PAulo");
        person.setGender("Male");

        return person;
    }

    public List<Person> findAll(){
        logger.info("Finding all persons");

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person Name: " + i);
        person.setLastName("Last name: " + i);
        person.setAddress("Person address: " + i);
        person.setGender("Male");
        return person;
    }
}
