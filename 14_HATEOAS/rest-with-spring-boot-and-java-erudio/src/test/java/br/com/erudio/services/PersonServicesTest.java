package br.com.erudio.services;

import br.com.erudio.data.dto.PersonDTO;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;
import br.com.erudio.unitetests.mapper.mocks.MockPerson;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    //inject mocks in service
    @InjectMocks
    private PersonServices services;

    //create mock of PersonRepository
    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUp() {

        //mocked input object
        input = new MockPerson();

        //open mocks
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(person));

        var result = services.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("GET")
                )
        );
        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") &&
                        link.getHref().endsWith("/api/person/v1") &&
                        link.getType().equals("GET")
                )
        );
assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") &&
                        link.getHref().endsWith("/api/person/v1") &&
                        link.getType().equals("POST")
                )
        );
assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") &&
                        link.getHref().endsWith("/api/person/v1") &&
                        link.getType().equals("PUT")
                )
        );
assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete") &&
                        link.getHref().endsWith("/api/person/v1/1") &&
                        link.getType().equals("DELETE")
                )
        );

    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
}
