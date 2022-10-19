package br.com.rest_apis.service;

import br.com.rest_apis.controller.PersonController;
import br.com.rest_apis.dto.v1.PersonDto;
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

    private final Logger logger = Logger.getLogger(PersonService.class.getName());
    public static final String NO_RECORDS = "No records found for this id";

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public PersonDto findById(Long id){
        logger.info("Finding one person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NO_RECORDS));

        PersonDto personDto =  DozerMapper.parseObject(entity, PersonDto.class);
        personDto.add(linkTo(methodOn(PersonController.class).findById(personDto.getKey())).withSelfRel());
        return personDto;
    }

    public List<PersonDto> findAll(){
        logger.info("Finding all persons");

        List<PersonDto> personDto = DozerMapper.parseListObjects(repository.findAll(), PersonDto.class);
        personDto.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return personDto;
    }


    public PersonDto create(PersonDto personDto){
        logger.info("Creating one persons");

        Person entity = DozerMapper.parseObject(personDto, Person.class);

        PersonDto dto = DozerMapper.parseObject(repository.save(entity), PersonDto.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

    /*public PersonDtoV2 createV2(PersonDtoV2 personDtoV2){
        logger.info("Creating one persons");
        PersonDto personDtoV2 = mapper.convertDtoToEntity(personDtoV2);
        return mapper.convertEntityToDto(repository.save(personDtoV2));
    }*/

    public PersonDto update(PersonDto dto){
        logger.info("Updating one persons");
        Person entity = repository.findById(dto.getKey())
                .orElseThrow(() -> new ResourceNotFoundException(NO_RECORDS));

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());

        PersonDto personDto = DozerMapper.parseObject(repository.save(entity), PersonDto.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());

        return personDto;
    }

    public void delete(Long id){
        logger.info("Deleting one person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NO_RECORDS));

        repository.delete(entity);

    }
}
