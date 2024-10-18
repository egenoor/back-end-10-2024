package ee.ege.veebipood.controller;

import ee.ege.veebipood.entity.Order;
import ee.ege.veebipood.entity.Person;
import ee.ege.veebipood.repository.AddressRepository;
import ee.ege.veebipood.repository.OrderRepository;
import ee.ege.veebipood.repository.PersonRepository;
import ee.ege.veebipood.service.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    PersonService personService;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("signup")
    public String signup(@RequestBody Person person){
        personService.savePerson(person);
        return "sisselogimise token";
    }
}
