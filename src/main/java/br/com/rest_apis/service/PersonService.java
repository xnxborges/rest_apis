package br.com.rest_apis.service;

import br.com.rest_apis.controller.PersonController;
import br.com.rest_apis.dto.v1.PersonDto;
import br.com.rest_apis.dto.v2.PersonDtoV2;
import br.com.rest_apis.exceptions.ResourceNotFoundException;
import br.com.rest_apis.mapper.DozerMapper;
import br.com.rest_apis.mapper.custom.PersonMapper;
import br.com.rest_apis.model.Person;
import br.com.rest_apis.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public PersonDto findById(Long id){
        logger.info("Finding one person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        PersonDto dto =  DozerMapper.parseObject(entity, PersonDto.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

    public List<PersonDto> findAll(){
        logger.info("Finding all persons");

        List<PersonDto> dto = DozerMapper.parseListObjects(repository.findAll(), PersonDto.class);
        dto.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return dto;
    }


    public PersonDto create(PersonDto person){
        logger.info("Creating one persons");

        Person entity = DozerMapper.parseObject(person, Person.class);

        PersonDto dto = DozerMapper.parseObject(repository.save(entity), PersonDto.class);
        dto.add(linkTo(methodOn(PersonController.class).create(person)).withSelfRel());
        return dto;
    }

    public PersonDtoV2 createV2(PersonDtoV2 person){
        logger.info("Creating one persons");

        Person entity = mapper.convertDtoToEntity(person);

        PersonDtoV2 dtoV2 = mapper.convertEntityToDto(repository.save(entity));

        return dtoV2;
    }

    public PersonDto update(PersonDto person){
        logger.info("Updating one persons");
        Person entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonDto dto = DozerMapper.parseObject(repository.save(entity), PersonDto.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());

        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting one person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        repository.delete(entity);

    }
}
