package fr.epsi.service.product;

import fr.epsi.service.product.dto.ProductDto;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductClient productClient;

    @InjectMocks
    ProductService productService;

    @Nested
    class getAll {

        @Test
        void shouldReturnProducts_whenGetAllIsCalled() {
            Product product1 = new Product(1, LocalDateTime.now(), "Product1", 100.0f, "Description1", "Red", 10);
            Product product2 = new Product(2, LocalDateTime.now(), "Product2", 150.0f, "Description2", "Blue", 20);
            List<Product> mockProducts = List.of(product1, product2);

            when(productClient.getAll()).thenReturn(mockProducts);

            List<Product> actualProducts = productService.getAll();

            assertEquals(mockProducts, actualProducts);
            verify(productClient, times(1)).getAll();
        }
    }

    @Nested
    class getById {

        @Test
        void shouldReturnProduct_whenGetByIdIsCalled() {
            Integer productId = 1;
            Product expectedProduct = new Product(productId, LocalDateTime.now(), "Product1", 100.0f, "Description1", "Red", 10);

            when(productClient.getById(productId)).thenReturn(expectedProduct);

            Product actualProduct = productService.getById(productId);

            assertEquals(expectedProduct, actualProduct);
            verify(productClient, times(1)).getById(productId);
        }
    }

    @Nested
    class update {

        @Test
        void shouldReturnUpdatedProduct_whenUpdateIsCalled() {
            Integer productId = 1;
            ProductDto updateProductDto = new ProductDto("UpdatedProduct", 200.0f, "UpdatedDescription", "Green", 15);
            Product updatedProduct = new Product(productId, LocalDateTime.now(), "UpdatedProduct", 200.0f, "UpdatedDescription", "Green", 15);

            when(productClient.update(productId, updateProductDto)).thenReturn(updatedProduct);

            Product actualUpdatedProduct = productService.update(productId, updateProductDto);

            assertEquals(updatedProduct, actualUpdatedProduct);
            verify(productClient, times(1)).update(productId, updateProductDto);
        }
    }

    @Nested
    class create {

        @Test
        void shouldReturnCreatedProduct_whenCreateIsCalled() {
            ProductDto createProductDto = new ProductDto("NewProduct", 300.0f, "NewDescription", "Yellow", 25);
            Product createdProduct = new Product(3, LocalDateTime.now(), "NewProduct", 300.0f, "NewDescription", "Yellow", 25);

            when(productClient.create(createProductDto)).thenReturn(createdProduct);

            Product actualCreatedProduct = productService.create(createProductDto);

            assertEquals(createdProduct, actualCreatedProduct);
            verify(productClient, times(1)).create(createProductDto);
        }
    }

    @Nested
    class delete{

        @Test
        void shouldDeleteProduct_whenDeleteIsCalled() {
            Integer productId = 1;

            doNothing().when(productClient).delete(productId);

            productService.delete(productId);

            verify(productClient, times(1)).delete(productId);
        }
    }
}
