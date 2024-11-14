package ee.ege.veebipood.controller;

import ee.ege.veebipood.entity.Person;
import ee.ege.veebipood.exception.ValidationException;
import ee.ege.veebipood.model.EmailPassword;
import ee.ege.veebipood.model.Token;
import ee.ege.veebipood.repository.OrderRepository;
import ee.ege.veebipood.repository.PersonRepository;
import ee.ege.veebipood.service.AuthService;
import ee.ege.veebipood.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Log4j2
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
    public ResponseEntity<Token> signup(@RequestBody Person person) throws ValidationException {
        authService.validate(person);
        String hashedPassword = encoder.encode(person.getPassword());
        person.setPassword(hashedPassword);
        personService.savePerson(person);
        Person savedPerson = personService.savePerson(person);
        return ResponseEntity.ok().body(authService.getToken(savedPerson));
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

    @GetMapping("admin")
    public boolean isAdmin() {
        GrantedAuthority authority = new SimpleGrantedAuthority("admin");
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(authority);
    }
}
