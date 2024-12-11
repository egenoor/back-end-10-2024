package ee.ege.veebipood.controller;

import ee.ege.veebipood.cache.ProductCache;
import ee.ege.veebipood.entity.Product;
import ee.ege.veebipood.exception.ValidationException;
import ee.ege.veebipood.repository.ProductRepository;
import ee.ege.veebipood.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    @Autowired
    ProductService productService;

    @Autowired
    ProductCache productCache;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll(); //SELECT * FROM product
    }

    @GetMapping("/public-products")
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @GetMapping("/product")
    public Product getProduct(@RequestParam Long id) throws ExecutionException {
        return productCache.getProduct(id);
        // return productRepository.findById(id).orElse(null); // .get() ja .orElseThrow() on samad asjad
    }

    // add
//    @PostMapping("/add-product")
//    public List<Product> addProduct(@RequestParam String name, @RequestParam double price) {
//        productRepository.save(new Product(name));
//        return productRepository.findAll();
//    }

    @PostMapping("/products")
    public List<Product> saveProduct(@RequestBody Product product) throws ValidationException {
        productService.validateProduct(product);
        if (productRepository.findById(product.getId()).isEmpty()) {
            productRepository.save(product);
        }
        return productRepository.findAll();
    }

    @PutMapping("/products")
    public List<Product> editProduct(@RequestBody Product product) throws ValidationException {
        productService.validateProduct(product);
        if (productRepository.findById(product.getId()).isPresent()) {
            productRepository.save(product);
            productCache.emptyCache();
        }
        return productRepository.findAllByOrderByIdAsc();
    }

    // localhost:8080/delete-product?name=Vichy&kategooria=Vesi <- järjekord pole tähtis
    // localhost:8080/delete-product/Vichy/vesi <- järjekord on tähtis
    // delete
    @DeleteMapping("/delete-product/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        productCache.emptyCache();
        return productRepository.findAll();
    }

    @GetMapping("/find-by-name")
    public Page<Product> findProductsByName(@RequestParam String name, Pageable pageable) {
        return productRepository.findByNameContainsIgnoreCase(name, pageable);
    }

}
