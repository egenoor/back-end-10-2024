package ee.ege.veebipood.controller;

import ee.ege.veebipood.entity.Product;
import ee.ege.veebipood.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // API päringute jaoks
public class ProductController {

//    List<Product> products = new ArrayList<>(Arrays.asList(
//            new Product("Coca"),
//            new Product("Fanta"),
//            new Product("Sprite")
//    ));
// List<String> productsEmpty = new ArrayList<>();

    @Autowired
    ProductRepository productRepository;

    // localhost:8080/products
    @GetMapping("/all-products")

    public List<Product> getAllProducts() {
//        log.info(SecurityContextHolder.getContext().getAuthentication().getCredentials());
//        log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        log.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return productRepository.findAll(); //SELECT * FROM product
    }

    @GetMapping("/products")
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @GetMapping("/product")
    public Product getProduct(@RequestParam Long id) {
        return productRepository.findById(id).orElseThrow(); // .get() ja .orElseThrow() on samad asjad
    }

    // add
    @GetMapping("/add-product")
    public List<Product> addProduct(@RequestParam String name, @RequestParam double price) {
        productRepository.save(new Product(name));
        return productRepository.findAll();
    }

    @PostMapping("/product")
    public List<Product> saveProduct(@RequestBody Product product) {
        productRepository.save(product);
        return productRepository.findAll();
    }

    // localhost:8080/delete-product?name=Vichy&kategooria=Vesi <- järjekord pole tähtis
    // localhost:8080/delete-product/Vichy/vesi <- järjekord on tähtis
    // delete
    @GetMapping("/delete-product/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @GetMapping("/find-by-name")
    public Page<Product> findProductsByName(@RequestParam String name, Pageable pageable) {
        return productRepository.findByNameContainsIgnoreCase(name, pageable);
    }

}
