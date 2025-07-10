package fr.epsi.service.product;

import fr.epsi.service.config.TestSecurityConfig;
import fr.epsi.service.product.dto.ProductDto;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestSecurityConfig.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

   @Nested
   class getAllProducts{
       @Test
       void shouldReturnListOfProducts() throws Exception {
           List<Product> products = Arrays.asList(
                   new Product(1, LocalDateTime.now(), "Product 1", 10.5f, "Description 1", "Red", 100),
                   new Product(2, LocalDateTime.now(), "Product 2", 20.0f, "Description 2", "Blue", 50)
           );
           when(productService.getAll()).thenReturn(products);

           mockMvc.perform(get("/api/products"))
                   .andExpect(status().isOk())
                   .andExpect(jsonPath("$.length()").value(2))
                   .andExpect(jsonPath("$[0].name").value("Product 1"))
                   .andExpect(jsonPath("$[1].name").value("Product 2"));
       }
   }

    @Nested
    class getProductById {
        @Test
        void shouldReturnProductWhenExists() throws Exception {
            Product product = new Product(1, LocalDateTime.now(), "Product 1", 10.5f, "Description 1", "Red", 100);
            when(productService.getById(1)).thenReturn(product);

            mockMvc.perform(get("/api/products/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.name").value("Product 1"))
                    .andExpect(jsonPath("$.price").value(10.5f))
                    .andExpect(jsonPath("$.description").value("Description 1"))
                    .andExpect(jsonPath("$.color").value("Red"))
                    .andExpect(jsonPath("$.stock").value(100));
        }

        @Test
        void shouldReturnNotFoundWhenProductDoesNotExist() throws Exception {
            when(productService.getById(999)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

            mockMvc.perform(get("/api/products/999"))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.error").doesNotExist()); // Adjust depending on response structure
        }
    }


    @Nested
    class updateProductById {
        @Test
        void shouldUpdateProductSuccessfully() throws Exception {
            ProductDto productDto = new ProductDto("Updated Product", 15.5f, "Updated Description", "Green", 70);
            Product updatedProduct = new Product(1, LocalDateTime.now(), "Updated Product", 15.5f, "Updated Description", "Green", 70);
            when(productService.update(1, productDto)).thenReturn(updatedProduct);

            mockMvc.perform(put("/api/products/1")
                            .contentType("application/json")
                            .content("{\"name\":\"Updated Product\",\"price\":15.5,\"description\":\"Updated Description\",\"color\":\"Green\",\"stock\":70}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.name").value("Updated Product"))
                    .andExpect(jsonPath("$.price").value(15.5f))
                    .andExpect(jsonPath("$.description").value("Updated Description"))
                    .andExpect(jsonPath("$.color").value("Green"))
                    .andExpect(jsonPath("$.stock").value(70));
        }

        @Test
        void shouldReturnNotFoundWhenProductToUpdateDoesNotExist() throws Exception {
            ProductDto productDto = new ProductDto("Non-Existent Product", 10f, "Non-Existent Description", "Yellow", 0);
            when(productService.update(999, productDto)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

            mockMvc.perform(put("/api/products/999")
                            .contentType("application/json")
                            .content("{\"name\":\"Non-Existent Product\",\"price\":10,\"description\":\"Non-Existent Description\",\"color\":\"Yellow\",\"stock\":0}"))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.error").doesNotExist()); // Adjust according to actual error response
        }
    }

    @Nested
    class createProduct {
        @Test
        void shouldCreateProductSuccessfully() throws Exception {
            ProductDto productDto = new ProductDto("New Product", 12.5f, "New Description", "Yellow", 150);
            Product createdProduct = new Product(3, LocalDateTime.now(), "New Product", 12.5f, "New Description", "Yellow", 150);
            when(productService.create(productDto)).thenReturn(createdProduct);

            mockMvc.perform(post("/api/products")
                            .contentType("application/json")
                            .content("{\"name\":\"New Product\",\"price\":12.5,\"description\":\"New Description\",\"color\":\"Yellow\",\"stock\":150}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(3))
                    .andExpect(jsonPath("$.name").value("New Product"))
                    .andExpect(jsonPath("$.price").value(12.5f))
                    .andExpect(jsonPath("$.description").value("New Description"))
                    .andExpect(jsonPath("$.color").value("Yellow"))
                    .andExpect(jsonPath("$.stock").value(150));
        }

        @Test
        void shouldReturnBadRequestWhenProductDtoIsInvalid() throws Exception {
            mockMvc.perform(post("/api/products")
                            .contentType("application/json")
                            .content("{\"name\":\"\",\"description\":\"\",\"color\":\"\",\"stock\":-10}"))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class deleteProduct {
        @Test
        void shouldDeleteProductSuccessfully() throws Exception {
            mockMvc.perform(delete("/api/products/1"))
                    .andExpect(status().isNoContent());
        }

        @Test
        void shouldReturnNotFoundWhenProductDoesNotExist() throws Exception {
            Integer id = 999;

            doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product 999 not found"))
                    .when(productService).delete(id);

            mockMvc.perform(delete("/api/products/999"))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.error").doesNotExist()); // Adjust according to actual error response
        }
    }
}