package ee.ege.veebipood.controller;

import ee.ege.veebipood.dto.PersonAddressDTO;
import ee.ege.veebipood.entity.Address;
import ee.ege.veebipood.entity.Person;
import ee.ege.veebipood.repository.OrderRepository;
import ee.ege.veebipood.repository.PersonRepository;
import ee.ege.veebipood.service.OrderService;
import ee.ege.veebipood.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    // localhost:8080/address?street=ta
    @GetMapping("/address")
    public List<PersonAddressDTO> getProducts(@RequestParam String street) {
        log.info("error: {}", street);
        List<Person> persons = personRepository.findByAddress_StreetContainsIgnoreCase(street);
        return personService.getPersonAddressDTO(persons);
    }
}
