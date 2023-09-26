package ru.bmstu.kibamba.distributedsystemlw01.repository;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.org.bouncycastle.util.Strings;
import ru.bmstu.kibamba.distributedsystemlw01.config.DatabaseTestConfiguration;
import ru.bmstu.kibamba.distributedsystemlw01.entity.Person;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest(properties = {"logging.level.org.testcontainers=debug"})
@ContextConfiguration(classes = {DatabaseTestConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;


    @ParameterizedTest
    @MethodSource("factory")
    public void findByName(String name) {
        Random random = new Random();
        //given
        factory().map(it -> new Person(it, random.nextInt(60 - 18 + 1) + 18, "Kinshasa", "DevOps"))
                .forEach(personRepository::save);
        //when
        var person = personRepository.findByName(name);

        //then
        assertThat(person.isPresent()).isTrue();
        assertThat(person.get()).extracting("name").isEqualTo(name);
    }

    @ParameterizedTest
    @ValueSource(ints = {18})
    public void deletePerson(int age) {
        //given
        var savedPerson = personRepository.save(new Person("Alex", age, "Moscow", "DevOps"));
        var savedId = savedPerson.getId();
        //when
        personRepository.deleteById(savedId);

        //then
        assertThat(personRepository.findById(savedId).isEmpty()).isTrue();

    }

    @ParameterizedTest
    @ValueSource(strings = {"Oleg", "Arthur"})
    public void updatePerson(String newName) {
        //given
        var savedPerson = personRepository.save(new Person("Alex", 18, "Moscow", "DevOps"));

        //when
        savedPerson.setName(newName);
        var updatedPerson = personRepository.save(savedPerson);

        //then
        assertThat(updatedPerson).extracting("name").isEqualTo(newName);
    }

    public static Stream<String> factory() {
        return Stream.of("Arthur", "Alex", "Oleg");
    }
}
