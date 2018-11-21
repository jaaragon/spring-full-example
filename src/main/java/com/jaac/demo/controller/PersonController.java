package com.jaac.demo.controller;

import com.jaac.demo.entities.Person;
import com.jaac.demo.exceptions.PersonNotFoundException;
import com.jaac.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    public PersonRepository personRepository;

    // Get All People
    @GetMapping("/person")
    public List<Person> getAllPeople() {
        return personRepository.findAll(); //TODO Falta paginado
    }

    // Create a person
    @PostMapping("/person")
    public Person addPerson(@Valid @RequestBody Person person) {
        return personRepository.save(person);
    }

    // Get a Single Person
    @GetMapping("/person/{id}")
    public Person getPersonById(@PathVariable(value = "id") Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person", "id", id));
    }

    // Update a Person
    @PutMapping("/person/{id}")
    public Person updatePerson(@PathVariable(value = "id") Long id,
                           @Valid @RequestBody Person personDetails) {

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person", "id", id));

        person.setName(personDetails.getName());
        person.setLastName(personDetails.getLastName());

        Person updatePerson = personRepository.save(person);
        return updatePerson;
    }

    // Delete a Person
    @DeleteMapping("/person/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person", "id", id));

        personRepository.delete(person);

        return ResponseEntity.ok().build();
    }
}
