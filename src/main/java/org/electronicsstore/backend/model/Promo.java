package org.electronicsstore.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private Double discountRate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToMany
    @JoinTable(name = "promo_product_item_m2m",
        joinColumns = @JoinColumn(name = "promo_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "product_item_id", referencedColumnName = "id")
    )
    private Set<ProductItem> productItems;

    
}
