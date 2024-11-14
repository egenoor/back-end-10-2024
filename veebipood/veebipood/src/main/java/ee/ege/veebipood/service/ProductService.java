package ee.ege.veebipood.service;

import ee.ege.veebipood.entity.Product;

import ee.ege.veebipood.exception.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public void validateProduct(Product product) throws ValidationException {
        if(product.getName() == null || product.getName().isBlank()) {
            throw new ValidationException("Product name cannot be empty");
        }
    }
}
