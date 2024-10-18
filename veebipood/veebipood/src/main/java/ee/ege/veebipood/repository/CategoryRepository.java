package ee.ege.veebipood.repository;

import ee.ege.veebipood.entity.Category;
import ee.ege.veebipood.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
