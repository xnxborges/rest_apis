package br.com.rest_apis.helper;

import br.com.rest_apis.dto.v1.PersonDto;
import br.com.rest_apis.model.Person;

import java.util.ArrayList;
import java.util.List;


public class MockPerson {


    public static Person getMockEntity() {
        return getMockEntity(0);
    }
    
    public static PersonDto getMockDto() {
        return getMockDto(0);
    }
    
    public static List<Person> getMockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            persons.add(getMockEntity(i));
        }
        return persons;
    }

    public static List<PersonDto> getMockDtoList() {
        List<PersonDto> personsDto = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            personsDto.add(getMockDto(i));
        }
        return personsDto;
    }
    
    public static Person getMockEntity(Integer number) {
        Person person = new Person();
        person.setId(number.longValue());
        person.setFirstName("First Name Test" + number);
        person.setLastName("Last Name Test" + number);
        person.setAddress("Address Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        return person;
    }

    public static PersonDto getMockDto(Integer number) {
        PersonDto personDto = new PersonDto();
        personDto.setKey(number.longValue());
        personDto.setFirstName("First Name Test" + number);
        personDto.setLastName("Last Name Test" + number);
        personDto.setAddress("Address Test" + number);
        personDto.setGender(((number % 2)==0) ? "Male" : "Female");
        return personDto;
    }

}
