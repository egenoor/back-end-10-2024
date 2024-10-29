package ee.ege.veebipood.repository;

import ee.ege.veebipood.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory_Id(Long id);

    Page<Product> findByNameContainsIgnoreCase(String name, Pageable pageable);

//    @Query("SELECT * FROM products WHERE category_id = :id")
//    List<Product> findAllProductsByCategoryId(@Param("id") Long id);
}
