package ee.ege.veebipood.controller;

import ee.ege.veebipood.entity.Person;
import ee.ege.veebipood.repository.OrderRepository;
import ee.ege.veebipood.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
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
