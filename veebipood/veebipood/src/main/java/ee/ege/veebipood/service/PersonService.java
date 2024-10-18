package ee.ege.veebipood.service;

import ee.ege.veebipood.entity.Address;
import ee.ege.veebipood.entity.Person;
import ee.ege.veebipood.repository.AddressRepository;
import ee.ege.veebipood.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // teeb Beaniks (serveris luuakse 1 kord), võimalik autowired teha
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional // salvestab ära kõik mis on funktsiooni sees, aga kui kasvõi 1 feilib
    // siis keerab kogu asja tagasi
    public void savePerson(Person person) {
//        Address address = addressRepository.save(person.getAddress());
//        person.setAddress(address);
        personRepository.save(person);
    }
}
