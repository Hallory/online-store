package org.electronicsstore.backend.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "category_id"}))
public class Characteristic {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    private String dataType;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}) // or m2m if restricted variety of products is chosen
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "characteristic", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CharacteristicValue> characteristicValues;

    public Set<CharacteristicValue> getCharacteristicValues() {
        return (characteristicValues == null) ? characteristicValues = new HashSet<>() : characteristicValues;
    }

    public void addCharacteristicValue(CharacteristicValue o) {
        getCharacteristicValues().add(o);
        o.setCharacteristic(this);
    }

    public void removeCharacteristicValue(CharacteristicValue o) {
        getCharacteristicValues().remove(o);
        o.setCharacteristic(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Characteristic that = (Characteristic) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
