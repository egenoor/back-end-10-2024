package ee.ege.veebipood.repository;

import ee.ege.veebipood.dto.PersonAddressDTO;
import ee.ege.veebipood.entity.Address;
import ee.ege.veebipood.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, String> {
    List<Person> findByAddress_StreetContainsIgnoreCase(String street);

}