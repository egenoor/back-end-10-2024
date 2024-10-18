package ee.ege.veebipood.repository;

import ee.ege.veebipood.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}