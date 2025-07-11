package fr.epsi.service.order;

import fr.epsi.service.order.dto.OrderDto;
import fr.epsi.service.product.Product;
import fr.epsi.service.product.ProductService;
import fr.epsi.service.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/api/orders")
    public List<Order> getAllOrders() {
        return orderService.getAll();
    }

    @GetMapping("/api/orders/{id}")
    public Order getOrderById(@PathVariable Integer id){
        return orderService.getById(id);
    }

    @PutMapping("/api/orders/{id}")
    public Order updateOrderById(@PathVariable Integer id, @RequestBody OrderDto orderDto){
        return orderService.update(id, orderDto);
    }

    @PostMapping("/api/orders")
    public Order createOrder(@RequestBody OrderDto orderDto){
        return orderService.create(orderDto);
    }

    @DeleteMapping("/api/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id){
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
    }
}
