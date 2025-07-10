package fr.epsi.service.product;

import fr.epsi.service.product.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "productClient", url = "${feign.product.service.url}")
public interface ProductClient {

    @GetMapping("/api/products")
    List<Product> getAll();

    @GetMapping("/api/products/{id}")
    Product getById(@PathVariable("id") Integer id);

    @PostMapping("/api/products")
    Product create(@RequestBody ProductDto dto);

    @PutMapping("/api/products/{id}")
    Product update(@PathVariable("id") Integer id, @RequestBody ProductDto dto);

    @DeleteMapping("/api/products/{id}")
    void delete(@PathVariable("id") Integer id);
}
