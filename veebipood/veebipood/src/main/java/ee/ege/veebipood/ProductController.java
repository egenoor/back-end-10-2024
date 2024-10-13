package ee.ege.veebipood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll(); //SELECT * FROM product
    }

    // add
    @GetMapping("/add-product")
    public List<Product> addProduct(@RequestParam String name, @RequestParam double price) {
        productRepository.save(new Product(name));
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

}
