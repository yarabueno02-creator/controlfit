package com.example.Controlfit.repository;

import com.example.Controlfit.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByCpf(String cpf);
    List<Customer> findByStatus(Customer.CustomerStatus status);
    List<Customer> findByNameContainingIgnoreCase(String name);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}