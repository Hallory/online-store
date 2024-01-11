package org.electronicsstore.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String accountId;
    @Column(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private String middleName;
    @Column(unique = true)
    private String email;
    private String phoneNum;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "customer")
    private Set<Address> addresses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(accountId, customer.accountId) && Objects.equals(username, customer.username) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, username, email);
    }
}
