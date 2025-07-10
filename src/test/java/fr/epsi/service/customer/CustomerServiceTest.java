package fr.epsi.service.customer;

import fr.epsi.service.customer.dto.CustomerDto;
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
public class CustomerServiceTest {

    @Mock
    CustomerClient customerClient;

    @InjectMocks
    CustomerService customerService;

    @Nested
    class getAll {
        @Test
        void shouldReturnListOfCustomers() {
            Customer customer1 = new Customer(1, LocalDateTime.now(), "username1", "firstname1", "lastname1", "123", "City", "company1");
            Customer customer2 = new Customer(2, LocalDateTime.now(), "username2", "firstname2", "lastname2", "123", "City", "company2");
            Customer customer3 = new Customer(3, LocalDateTime.now(), "username3", "firstname3", "lastname3", "123", "City", "company3");

            List<Customer> customerList = List.of(customer1, customer2, customer3);
            when(customerClient.getAll()).thenReturn(customerList);

            List<Customer> result = customerService.getAll();
            assertEquals(result, customerList);
        }
    }

    @Nested
    class getById {
        @Test
        void shouldReturnCustomer() {
            Customer customer1 = new Customer(1, LocalDateTime.now(), "username1", "firstname1", "lastname1", "123", "City", "company1");

            when(customerClient.getById(1)).thenReturn((customer1));

            Customer result = customerService.getById(1);

            assertEquals(result, customer1);
        }
    }

    @Nested
    class update{
        @Test
        void shouldUpdateCustomer() {

            Integer id = 1;
            CustomerDto updateDto = new CustomerDto("newUsername", "newFirst", "newLast", "12345", "NewCity", "NewCompany");
            Customer expectedCustomer = new Customer(id, LocalDateTime.now(), "newUsername", "newFirst", "newLast", "12345", "NewCity", "NewCompany");

            when(customerClient.update(id, updateDto)).thenReturn(expectedCustomer);

            Customer result = customerService.update(id, updateDto);

            assertEquals(expectedCustomer.getId(), result.getId());
            assertEquals(expectedCustomer.getCreatedAt(), result.getCreatedAt());
            assertEquals(expectedCustomer.getUsername(), result.getUsername());
            assertEquals(expectedCustomer.getFirstname(), result.getFirstname());
            assertEquals(expectedCustomer.getLastname(), result.getLastname());
            assertEquals(expectedCustomer.getPostalCode(), result.getPostalCode());
            assertEquals(expectedCustomer.getCity(), result.getCity());
            assertEquals(expectedCustomer.getCompanyName(), result.getCompanyName());
        }
    }

    @Nested
    class create{
        @Test
        void shouldCreateCustomer() {

            Integer id = 1;
            Customer expectedCustomer = new Customer(id, LocalDateTime.now(), "newUsername", "newFirst", "newLast", "12345", "NewCity", "NewCompany");
            CustomerDto createCustomerDto = new CustomerDto("newUsername", "newFirst", "newLast", "12345", "NewCity", "NewCompany");

            when(customerClient.create(createCustomerDto)).thenReturn(expectedCustomer);

            Customer result = customerService.create(createCustomerDto);

            assertEquals(expectedCustomer.getId(), result.getId());
            assertEquals(expectedCustomer.getCreatedAt(), result.getCreatedAt());
            assertEquals(expectedCustomer.getUsername(), result.getUsername());
            assertEquals(expectedCustomer.getFirstname(), result.getFirstname());
            assertEquals(expectedCustomer.getLastname(), result.getLastname());
            assertEquals(expectedCustomer.getPostalCode(), result.getPostalCode());
            assertEquals(expectedCustomer.getCity(), result.getCity());
            assertEquals(expectedCustomer.getCompanyName(), result.getCompanyName());
        }
    }
    @Nested
    class delete{
        @Test
        void shouldDeleteCustomer() {

            doNothing().when(customerClient).delete(1);

            customerService.delete(1);

            verify(customerClient, times(1)).delete(1);
        }
    }
}
