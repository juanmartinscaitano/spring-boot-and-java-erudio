package br.com.erudio.services;

import br.com.erudio.repository.PersonRepository;
import br.com.erudio.unitetests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

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
