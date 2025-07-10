package fr.epsi.service.product;

import fr.epsi.service.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductClient productClient;

    public List<Product> getAll() {
        return productClient.getAll();
    }

    public Product getById(Integer id) {
        return productClient.getById(id);
    }

    public Product update(Integer id, ProductDto updateProductDto) {
        return productClient.update(id, updateProductDto);
    }

    public Product create(ProductDto createProductDto){
        return productClient.create(createProductDto);
    }

    public void delete(Integer id){
        productClient.delete(id);
    }
}
