package fr.epsi.service.customer;

import fr.epsi.service.customer.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerClient customerClient;

    public List<Customer> getAll() {
        return customerClient.getAll();
    }

    public Customer getById(Integer id) {
        return customerClient.getById(id);
    }

    public Customer update(Integer id, CustomerDto updateCustomerDto) {
        return customerClient.update(id, updateCustomerDto);
    }

    public Customer create(CustomerDto createCustomerDto){
        return customerClient.create(createCustomerDto);
    }

    public void delete(Integer id){
        customerClient.delete(id);
    }

}
