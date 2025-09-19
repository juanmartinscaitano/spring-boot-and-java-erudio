package br.com.erudio.services;


import br.com.erudio.data.dto.v1.PersonDTO;
import br.com.erudio.exception.ResourceNotFoundException;
import static br.com.erudio.mapper.ObjectMapper.parseListObject;
import static br.com.erudio.mapper.ObjectMapper.parseObject;

import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());


    @Autowired
    PersonRepository repository;


    public List<PersonDTO> findAll() {

        logger.info("Finding all People");

        return parseListObject(repository.findAll(), PersonDTO.class);

    }

    public PersonDTO findById(Long id) {
        logger.info("Finding one person");

        var entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records for this ID!")
        );
        return parseObject(entity, PersonDTO.class);

    }

    public PersonDTO create(PersonDTO personDTO) {

//       var entity = parseObject(person, Person.class);
//
//       entity.setFirstName(person.getFirstName());

        Person entity = new Person(personDTO);

        logger.info("creating one person");

        return parseObject(repository.save(entity), PersonDTO.class);

//        return repository.save(entity);
    }

    public PersonDTO update(PersonDTO person) {

        logger.info("Updating one person");

        Person entity = repository
                .findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found resource with id: " + person.getId()));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);

    }

    public void delete(Long id) {
        logger.info("Deleting one person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        repository.delete(entity);
    }
}
