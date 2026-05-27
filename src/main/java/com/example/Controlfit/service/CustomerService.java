package com.example.Controlfit.service;

import com.example.Controlfit.model.Customer;
import com.example.Controlfit.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
    }

    @Transactional
    public Customer create(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        if (customerRepository.existsByCpf(customer.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer update(Long id, Customer customerDetails) {
        Customer customer = findById(id);

        if (!customer.getEmail().equals(customerDetails.getEmail()) &&
            customerRepository.existsByEmail(customerDetails.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        if (!customer.getCpf().equals(customerDetails.getCpf()) &&
            customerRepository.existsByCpf(customerDetails.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        customer.setName(customerDetails.getName());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhone(customerDetails.getPhone());
        customer.setCpf(customerDetails.getCpf());
        customer.setAddress(customerDetails.getAddress());
        customer.setStatus(customerDetails.getStatus());

        return customerRepository.save(customer);
    }

    @Transactional
    public void delete(Long id) {
        Customer customer = findById(id);
        customerRepository.delete(customer);
    }
}