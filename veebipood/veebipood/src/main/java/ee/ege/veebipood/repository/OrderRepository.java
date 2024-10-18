package ee.ege.veebipood.repository;

import ee.ege.veebipood.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByPerson_Email(String email);
}
