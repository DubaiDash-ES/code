package com.example.consumerdash;

import java.util.*;
import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles("test")
class StateRepositoryTests {

    @Autowired
    private StateRepository repositoryState;

    @Autowired
    private TestEntityManager entityManager;

    // test DB

    @Test
    public void whenFindAll() {
        State firstState = new State();
        firstState.setIcao24("111222");
        entityManager.persist(firstState);
        entityManager.flush();

        State secondState = new State();
        secondState.setIcao24("222111");
        entityManager.persist(secondState);
        entityManager.flush();

        List<State> states = repositoryState.findAll();

        assertEquals(states.size(), 2);
        assertEquals(states.get(0), firstState);
    }

    @Test
    public void whenFindByIcao24(){
        State state = new State();
        state.setIcao24("555666");
        entityManager.persist(state);
        entityManager.flush();

        List <State> testState = (List<State>) repositoryState.findByIcao24(state.getIcao24());

        assertEquals(testState.get(0).getIcao24(), state.getIcao24());
    }

    @Test
    public void whenFindByOriginCountry(){
        State state = new State();
        state.setOrigin_country("Portugal");
        entityManager.persist(state);
        entityManager.flush();

        List<State> testState = (List<State>) repositoryState.findByorigin_country(state.getOrigin_country());

        assertEquals(testState.get(0).getOrigin_country(), state.getOrigin_country());
    }
}