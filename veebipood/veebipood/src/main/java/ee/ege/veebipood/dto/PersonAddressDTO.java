package ee.ege.veebipood.dto;

import ee.ege.veebipood.entity.Address;
import lombok.Data;

@Data
public class PersonAddressDTO { // DTO -> Data Transfer Object
    private String firstName;
    private String lastName;
    private Address address;
}
