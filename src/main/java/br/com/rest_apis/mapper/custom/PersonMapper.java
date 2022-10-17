package br.com.rest_apis.mapper.custom;

import br.com.rest_apis.dto.v2.PersonDtoV2;
import br.com.rest_apis.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonDtoV2 convertEntityToDto(Person person){
        PersonDtoV2 dtoV2 = new PersonDtoV2();
        dtoV2.setId(person.getId());
        dtoV2.setFirstName(person.getFirstName());
        dtoV2.setLastName(person.getLastName());
        dtoV2.setAddress(person.getAddress());
        dtoV2.setBirthDay(new Date());
        dtoV2.setGender(person.getGender());
        return dtoV2;

    }

    public Person convertDtoToEntity(PersonDtoV2 person){
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return entity;

    }
}
