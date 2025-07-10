package fr.epsi.service.product;

import fr.epsi.service.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/products")
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/api/products/{id}")
    public Product getProductById(@PathVariable Integer id){
        return productService.getById(id);
    }

    @PutMapping("/api/products/{id}")
    public Product updateProductById(@PathVariable Integer id, @RequestBody ProductDto productDto){
        return productService.update(id, productDto);
    }

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductDto productDto){
        return productService.create(productDto);
    }

    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
    }
}
