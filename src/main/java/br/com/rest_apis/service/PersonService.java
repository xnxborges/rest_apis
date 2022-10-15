package br.com.rest_apis.service;

import br.com.rest_apis.dto.v1.PersonDto;
import br.com.rest_apis.exceptions.ResourceNotFoundException;
import br.com.rest_apis.mapper.DozerMapper;
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

    public PersonDto findById(Long id){
        logger.info("Finding one person");

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        return DozerMapper.parseObject(entity, PersonDto.class);
    }

    public List<PersonDto> findAll(){
        logger.info("Finding all persons");

        return DozerMapper.parseListObjects(personRepository.findAll(), PersonDto.class);
    }


    public PersonDto create(PersonDto person){
        logger.info("Creating one persons");

        Person entity = DozerMapper.parseObject(person, Person.class);

        PersonDto dto = DozerMapper.parseObject(personRepository.save(entity), PersonDto.class);

        return dto;
    }

    public PersonDto update(PersonDto person){
        logger.info("Updating one persons");
        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonDto dto = DozerMapper.parseObject(personRepository.save(entity), PersonDto.class);

        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting one person");

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        personRepository.delete(entity);

    }
}
