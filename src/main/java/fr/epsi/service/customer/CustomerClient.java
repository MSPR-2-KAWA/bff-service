package fr.epsi.service.customer;

import fr.epsi.service.customer.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "customerClient", url = "${feign.customer.service.url}")
public interface CustomerClient {

    @GetMapping("/api/customers")
    List<Customer> getAll();

    @GetMapping("/api/customers/{id}")
    Customer getById(@PathVariable("id") Integer id);

    @PostMapping("/api/customers")
    Customer create(@RequestBody CustomerDto dto);

    @PutMapping("/api/customers/{id}")
    Customer update(@PathVariable("id") Integer id, @RequestBody CustomerDto dto);

    @DeleteMapping("/api/customers/{id}")
    void delete(@PathVariable("id") Integer id);
}
