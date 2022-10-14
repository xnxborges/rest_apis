package br.com.rest_apis.service;

import br.com.rest_apis.model.Person;
import org.springframework.stereotype.Service;

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
}
