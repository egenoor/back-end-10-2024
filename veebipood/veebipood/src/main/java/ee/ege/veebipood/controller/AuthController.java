package ee.ege.veebipood.controller;

import ee.ege.veebipood.entity.Person;
import ee.ege.veebipood.model.EmailPassword;
import ee.ege.veebipood.model.Token;
import ee.ege.veebipood.repository.OrderRepository;
import ee.ege.veebipood.repository.PersonRepository;
import ee.ege.veebipood.service.AuthService;
import ee.ege.veebipood.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    AuthService authService;

    @PostMapping("signup")
    public String signup(@RequestBody Person person){
        String hashedPassword = encoder.encode(person.getPassword());
        person.setPassword(hashedPassword);
        personService.savePerson(person);
        return "sisselogimise token";
    }

    @PostMapping("login")
    public Token login(@RequestBody EmailPassword emailPassword) {
        Person person = personRepository.findById(emailPassword.getEmail()).orElseThrow();
        if (encoder.matches(emailPassword.getPassword(), person.getPassword())) {
            return authService.getToken(person);
        }
        // pigem exceptioni väljaviskamine
        return new Token();
    }
}
