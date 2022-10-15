package br.com.rest_apis.controller;

import br.com.rest_apis.dto.v1.PersonDto;
import br.com.rest_apis.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto findById(@PathVariable(value = "id") Long id) {
        return personService.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDto> findAll() {
        return personService.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto create(@RequestBody PersonDto person) {
        return personService.create(person);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto update(@RequestBody PersonDto person) {
        return personService.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
