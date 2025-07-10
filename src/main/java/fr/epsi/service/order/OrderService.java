package fr.epsi.service.order;

import fr.epsi.service.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderClient orderClient;

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
        return orderClient.create(createOrderDto);
    }

    public void delete(Integer id){
        orderClient.delete(id);
    }
}
