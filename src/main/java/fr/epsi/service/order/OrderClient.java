package fr.epsi.service.order;

import fr.epsi.service.order.dto.OrderDto;
import fr.epsi.service.product.Product;
import fr.epsi.service.product.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "orderClient", url = "${feign.order.service.url}")
public interface OrderClient {

    @GetMapping("/api/orders")
    List<Order> getAll();

    @GetMapping("/api/orders/{id}")
    Order getById(@PathVariable("id") Integer id);

    @PostMapping("/api/orders")
    Order create(@RequestBody OrderDto dto);

    @PutMapping("/api/orders/{id}")
    Order update(@PathVariable("id") Integer id, @RequestBody OrderDto dto);

    @DeleteMapping("/api/orders/{id}")
    void delete(@PathVariable("id") Integer id);
}
