package org.electronicsstore.backend.model.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.electronicsstore.backend.model.order.ShopOrder;

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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private ShoppingCart shoppingCart;

    @OneToMany(mappedBy = "customer", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH }, orphanRemoval = false)
    private Set<ShopOrder> shopOrders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CustomerReview> customerReviews;

    public void addAddress(Address o) {
        addresses.add(o);
        o.setCustomer(this);
    }

    public void removeAddress(Address o) {
        addresses.remove(o);
        o.setCustomer(null);
    }

    public void addShopOrder(ShopOrder o) {
        shopOrders.add(o);
        o.setCustomer(this);
    }

    public void removeShopOrder(ShopOrder o) {
        shopOrders.remove(o);
        o.setCustomer(null);
    }

    public void addCustomerReview(CustomerReview o) {
        customerReviews.add(o);
        o.setCustomer(this);
    }

    public void removeShoppingCartItem(CustomerReview o) {
        customerReviews.remove(o);
        o.setCustomer(null);
    }

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
