package com.pichincha.sp.accounts.management.infrastructure.output.adapter;

import com.pichincha.sp.accounts.management.application.output.port.CustomerOutputPort;
import com.pichincha.sp.accounts.management.domain.*;
import com.pichincha.sp.accounts.management.infrastructure.exception.NotFoundException;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.database.MgmtCustomerRepository;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.database.MgmtPersonRepository;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtCustomer;
import com.pichincha.sp.accounts.management.infrastructure.output.repository.entity.MgmtPerson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.util.List;

import static com.pichincha.sp.accounts.management.domain.util.UtilClass.validateBean;

/**
 * @author bryancocha@gmail.com
 * @class_name CustomerOutputAdapter.java
 * @class_description
 * @create_date 27/09/2025 Copyright 2022.
 */
@Repository
@RequiredArgsConstructor
public class CustomerOutputAdapter implements CustomerOutputPort {

    private final MgmtCustomerRepository mgmtCustomerRepository;
    private final MgmtPersonRepository mgmtPersonRepository;
    @Override
    public List<Customer> getCustomerById(String identification) {
        if(identification == null || identification.isEmpty()){
            List<MgmtCustomer> customers = mgmtCustomerRepository.findAll();
            if (customers == null) {
                throw new NotFoundException("Not found");
            }
            return customers.stream()
                    .map(c -> Customer.builder()
                            .id(c.getPerson().getId().toString())
                            .mgmtCustomerId(c.getIdMgmtCustomer().toString())
                            .name(c.getPerson().getName())
                            .gender(c.getPerson().getGender())
                            .age(c.getPerson().getAge())
                            .identification(c.getPerson().getIdentification())
                            .address(c.getPerson().getAddress())
                            .phone(c.getPerson().getPhone())
                            .customerId(c.getCustomerId())
                            .password(c.getPassword())
                            .state(c.getState())
                            .build())
                    .collect(java.util.stream.Collectors.toList());

        }

        MgmtCustomer customerRetrieved = mgmtCustomerRepository.findByPersonIdentification(identification);
        if (customerRetrieved == null) {
            throw new NotFoundException("Not found");
        }

        return List.of(Customer.builder()
                .id(customerRetrieved.getPerson().getId().toString())
                .mgmtCustomerId(customerRetrieved.getIdMgmtCustomer().toString())
                .name(customerRetrieved.getPerson().getName())
                .gender(customerRetrieved.getPerson().getGender())
                .age(customerRetrieved.getPerson().getAge())
                .identification(customerRetrieved.getPerson().getIdentification())
                .address(customerRetrieved.getPerson().getAddress())
                .phone(customerRetrieved.getPerson().getPhone())
                .customerId(customerRetrieved.getCustomerId())
                .password(customerRetrieved.getPassword())
                .state(customerRetrieved.getState())
                .build());
    }

    @Override
    public Customer createCustomer(Customer customer) {
        validateBean(customer);

        MgmtCustomer entity = new MgmtCustomer();
        MgmtPerson person = new MgmtPerson();
        person.setName(customer.getName());
        person.setGender(customer.getGender());
        person.setAge(customer.getAge());
        person.setIdentification(customer.getIdentification());
        person.setAddress(customer.getAddress());
        person.setPhone(customer.getPhone());

        MgmtPerson savedPerson = mgmtPersonRepository.save(person);

        entity.setPerson(person);

        SecureRandom random = new SecureRandom();
        long number = Math.abs(random.nextLong()) % 100_000L; // 5 dígitos
        entity.setCustomerId(String.format("%05d", number));
        entity.setPassword(customer.getPassword());
        entity.setState(customer.getState());

        MgmtCustomer saved = mgmtCustomerRepository.save(entity);

        return Customer.builder()
                .id(saved.getPerson().getId().toString())
                .name(saved.getPerson().getName())
                .gender(saved.getPerson().getGender())
                .age(saved.getPerson().getAge())
                .identification(saved.getPerson().getIdentification())
                .address(saved.getPerson().getAddress())
                .phone(saved.getPerson().getPhone())
                .mgmtCustomerId(saved.getIdMgmtCustomer().toString())
                .customerId(saved.getCustomerId())
                .password(saved.getPassword())
                .state(saved.getState())
                .build();
    }

    @Override
    @Transactional
    public void deleteCustomer(String identification) {
        MgmtCustomer entity = mgmtCustomerRepository.findByPersonIdentification(identification);
        if (entity != null) {
            mgmtCustomerRepository.deleteByPersonIdentification(entity.getPerson().getIdentification());
            mgmtPersonRepository.deleteByIdentification(identification);

        }else {
            throw new NotFoundException("Not found");
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        MgmtCustomer entity = mgmtCustomerRepository.findByPersonIdentification(customer.getIdentification());
        if (entity == null) {
            throw new NotFoundException("Not found");
        }
        entity.getPerson().setName(customer.getName());
        entity.getPerson().setGender(customer.getGender());
        entity.getPerson().setAge(customer.getAge());
        entity.getPerson().setIdentification(customer.getIdentification());
        entity.getPerson().setAddress(customer.getAddress());
        entity.getPerson().setPhone(customer.getPhone());
        entity.setPassword(customer.getPassword());
        entity.setState(customer.getState());

        MgmtCustomer updatedEntity = mgmtCustomerRepository.save(entity);
        MgmtPerson updatedPerson = mgmtPersonRepository.save(entity.getPerson());

        return Customer.builder()
                .id(updatedPerson.getId().toString())
                .name(updatedPerson.getName())
                .gender(updatedPerson.getGender())
                .age(updatedPerson.getAge())
                .identification(updatedPerson.getIdentification())
                .address(updatedPerson.getAddress())
                .phone(updatedPerson.getPhone())
                .mgmtCustomerId(updatedEntity.getIdMgmtCustomer().toString())
                .customerId(updatedEntity.getCustomerId())
                .password(updatedEntity.getPassword())
                .state(updatedEntity.getState())
                .build();
    }

    @Override
    public Customer editCustomer(Customer customer) {
        MgmtCustomer entity = mgmtCustomerRepository.findByPersonIdentification(customer.getIdentification());
        if (entity == null) {
            throw new NotFoundException("Not found");
        }
        if (customer.getName() != null) entity.getPerson().setName(customer.getName());
        if (customer.getGender() != null) entity.getPerson().setGender(customer.getGender());
        if (customer.getAge() != null) entity.getPerson().setAge(customer.getAge());
        if (customer.getIdentification() != null) entity.getPerson().setIdentification(customer.getIdentification());
        if (customer.getAddress() != null) entity.getPerson().setAddress(customer.getAddress());
        if (customer.getPhone() != null) entity.getPerson().setPhone(customer.getPhone());
        if (customer.getPassword() != null) entity.setPassword(customer.getPassword());
        if (customer.getState() != null) entity.setState(customer.getState());

        MgmtCustomer updatedEntity = mgmtCustomerRepository.save(entity);
        MgmtPerson updatedPerson = mgmtPersonRepository.save(entity.getPerson());

        return Customer.builder()
                .id(updatedPerson.getId().toString())
                .name(updatedPerson.getName())
                .gender(updatedPerson.getGender())
                .age(updatedPerson.getAge())
                .identification(updatedPerson.getIdentification())
                .address(updatedPerson.getAddress())
                .phone(updatedPerson.getPhone())
                .mgmtCustomerId(updatedEntity.getIdMgmtCustomer().toString())
                .customerId(updatedEntity.getCustomerId())
                .password(updatedEntity.getPassword())
                .state(updatedEntity.getState())
                .build();
    }
}
