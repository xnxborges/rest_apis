package br.com.rest_apis.service;

import br.com.rest_apis.exceptions.ResourceNotFoundException;
import br.com.rest_apis.model.Person;
import br.com.rest_apis.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    public Person findById(Long id){
        logger.info("Finding one person");

        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
    }

    public List<Person> findAll(){
        logger.info("Finding all persons");

        return personRepository.findAll();
    }


    public Person create(Person person){
        logger.info("Creating one persons");
        return personRepository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating one persons");
        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(person);
    }

    public void delete(Long id){
        logger.info("Deleting one person");

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        personRepository.delete(entity);

    }
}
