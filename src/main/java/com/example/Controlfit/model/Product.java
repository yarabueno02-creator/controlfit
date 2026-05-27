package com.example.Controlfit.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do produto é obrigatório")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Categoria é obrigatória")
    @Column(nullable = false)
    private String category;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser positivo")
    @Column(nullable = false)
    private Double price;

    @NotNull(message = "Quantidade em estoque é obrigatória")
    @Column(nullable = false)
    private Integer stock = 0;

    @NotNull(message = "Estoque mínimo é obrigatório")
    @Column(name = "min_stock", nullable = false)
    private Integer minStock = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StockStatus status = StockStatus.NORMAL;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        updateStockStatus();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        updateStockStatus();
    }

    private void updateStockStatus() {
        if (stock <= minStock * 0.3) {
            status = StockStatus.CRITICAL;
        } else if (stock <= minStock) {
            status = StockStatus.LOW;
        } else {
            status = StockStatus.NORMAL;
        }
    }

    public enum StockStatus {
        NORMAL, LOW, CRITICAL, OUT_OF_STOCK
    }
}