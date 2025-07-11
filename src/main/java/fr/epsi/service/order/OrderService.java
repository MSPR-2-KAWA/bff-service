package fr.epsi.service.order;

import fr.epsi.service.order.dto.OrderDto;
import fr.epsi.service.order.dto.OrderProductItemDto;
import fr.epsi.service.product.Product;
import fr.epsi.service.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderClient orderClient;
    private final ProductClient productClient;

    public List<Order> getAll() {
        return orderClient.getAll();
    }

    public Order getById(Integer id) {
        return orderClient.getById(id);
    }

    public Order update(Integer id, OrderDto updateOrderDto) {
        return orderClient.update(id, updateOrderDto);
    }

    public Order create(OrderDto createOrderDto){
        for (OrderProductItemDto item : createOrderDto.getItems()) {
           Product product = productClient.getById(item.getProductId());
            if (product.getStock() < item.getQuantity()) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Stock insuffisant pour le produit ID : " + item.getProductId()
                );
            }
        }
        return orderClient.create(createOrderDto);
    }

    public void delete(Integer id){
        orderClient.delete(id);
    }
}
