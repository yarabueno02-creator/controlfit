package com.example.Controlfit.repository;

import com.example.Controlfit.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findByEmail(String email);
    Optional<Supplier> findByCnpj(String cnpj);
    List<Supplier> findByStatus(Supplier.SupplierStatus status);
    List<Supplier> findByNameContainingIgnoreCase(String name);
    boolean existsByEmail(String email);
    boolean existsByCnpj(String cnpj);
}