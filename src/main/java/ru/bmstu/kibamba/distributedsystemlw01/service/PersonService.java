package ru.bmstu.kibamba.distributedsystemlw01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bmstu.kibamba.distributedsystemlw01.entity.Person;
import ru.bmstu.kibamba.distributedsystemlw01.payload.request.PersonRequest;
import ru.bmstu.kibamba.distributedsystemlw01.payload.response.PersonResponse;
import ru.bmstu.kibamba.distributedsystemlw01.repository.PersonRepository;

import java.util.List;


public interface PersonService {
    PersonResponse getPerson(Long id);

    List<PersonResponse> getPersons();

    Long createPerson(PersonRequest request);

    PersonResponse editPerson(Long id, PersonRequest request);

    void deletePerson(Long id);
}
