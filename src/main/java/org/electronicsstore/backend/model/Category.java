package org.electronicsstore.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
