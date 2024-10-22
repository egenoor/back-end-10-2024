package ee.ege.veebipood.service;

import ee.ege.veebipood.dto.PersonAddressDTO;
import ee.ege.veebipood.entity.Person;
import ee.ege.veebipood.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service // teeb Beaniks (serveris luuakse 1 kord), võimalik autowired teha
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ModelMapper modelMapper;

    // @Transactional // salvestab ära kõik mis on funktsiooni sees, aga kui kasvõi 1 feilib
    // siis keerab kogu asja tagasi
    public void savePerson(Person person) {
//        Address address = addressRepository.save(person.getAddress());
//        person.setAddress(address);
        personRepository.save(person);
    }

    public List<PersonAddressDTO> getPersonAddressDTO(List<Person> persons) {
//        List<PersonAddressDTO> personAddressDTOS = new ArrayList<>();
//        for(Person p: persons) {
//            PersonAddressDTO dto = new PersonAddressDTO();
//            dto.setAddress(p.getAddress());
//            dto.setFirstName(p.getFirstName());
//            dto.setLastName(p.getLastName());
//            personAddressDTOS.add(dto);
//        }
//        ModelMapper modelMapper = new ModelMapper();
        System.out.println(modelMapper);
        PersonAddressDTO[] personAddressDTOs = modelMapper.map(persons, PersonAddressDTO[].class);
        return Arrays.asList(personAddressDTOs);
    }
}
