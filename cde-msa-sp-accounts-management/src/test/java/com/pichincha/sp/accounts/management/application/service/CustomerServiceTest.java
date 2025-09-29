package com.pichincha.sp.accounts.management.application.service;

import com.pichincha.sp.accounts.management.domain.Customer;
import com.pichincha.sp.accounts.management.application.output.port.CustomerOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {
    @Mock
    private CustomerOutputPort customerOutputPort;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomerById() {
        Customer customer = Customer.builder()
            .mgmtCustomerId("1")
            .name("Juan Perez")
            .identification("1234567890")
            .address("Av. Siempre Viva")
            .phone("0999999999")
            .state(true)
            .build();
        when(customerOutputPort.getCustomerById("1234567890"))
            .thenReturn(Collections.singletonList(customer));
        List<Customer> result = customerService.getCustomerById("1234567890");
        assertEquals(1, result.size());
        verify(customerOutputPort).getCustomerById("1234567890");
    }

    @Test
    void testCreateCustomer() {
        Customer customer = Customer.builder()
            .mgmtCustomerId("1")
            .name("Juan Perez")
            .identification("1234567890")
            .address("Av. Siempre Viva")
            .phone("0999999999")
            .state(true)
            .build();
        when(customerOutputPort.createCustomer(customer)).thenReturn(customer);
        Customer result = customerService.createCustomer(customer);
        assertNotNull(result);
        verify(customerOutputPort).createCustomer(customer);
    }

    @Test
    void testUpdateCustomer() {
        Customer customer = Customer.builder()
            .mgmtCustomerId("1")
            .name("Juan Perez")
            .identification("1234567890")
            .address("Av. Siempre Viva")
            .phone("0999999999")
            .state(true)
            .build();
        when(customerOutputPort.updateCustomer(customer)).thenReturn(customer);
        Customer result = customerService.updateCustomer(customer);
        assertNotNull(result);
        verify(customerOutputPort).updateCustomer(customer);
    }

    @Test
    void testDeleteCustomer() {
        doNothing().when(customerOutputPort).deleteCustomer("1234567890");
        customerService.deleteCustomer("1234567890");
        verify(customerOutputPort).deleteCustomer("1234567890");
    }

    @Test
    void testEditCustomer() {
        Customer customer = Customer.builder()
            .mgmtCustomerId("1")
            .name("Juan Perez")
            .identification("1234567890")
            .address("Av. Siempre Viva")
            .phone("0999999999")
            .state(true)
            .build();
        when(customerOutputPort.editCustomer(customer)).thenReturn(customer);
        Customer result = customerService.editCustomer(customer);
        assertNotNull(result);
        verify(customerOutputPort).editCustomer(customer);
    }
}
