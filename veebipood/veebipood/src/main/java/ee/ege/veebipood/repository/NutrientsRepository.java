package ee.ege.veebipood.repository;

import ee.ege.veebipood.entity.Nutrients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutrientsRepository extends JpaRepository<Nutrients, Long> {
}
