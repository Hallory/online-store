package org.electronicsstore.backend.model.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Country {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String countryName;

    @OneToMany(mappedBy = "country")
    private Set<Address> addresses;

    public void addAddress(Address o) {
        addresses.add(o);
        o.setCountry(this);
    }

    public void removeAddress(Address o) {
        addresses.remove(o);
        o.setCountry(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(countryName, country.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName);
    }
}
