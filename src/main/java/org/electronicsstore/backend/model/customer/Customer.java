package org.electronicsstore.backend.model.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.electronicsstore.backend.model.order.ShopOrder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    {
        isDeleted = false;
    }
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
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
    private Boolean isDeleted;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses;

    @ToString.Exclude
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ShoppingCart shoppingCart;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH }, orphanRemoval = false)
    private Set<ShopOrder> shopOrders;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    private Set<CustomerReview> customerReviews;

    public Set<Address> getAddresses() {
        return (addresses == null) ? addresses = new HashSet<>() : addresses;
    }

    public Set<ShopOrder> getShopOrders() {
        return (shopOrders == null) ? shopOrders = new HashSet<>() : shopOrders;
    }

    public Set<CustomerReview> getCustomerReviews() { return (customerReviews == null) ? customerReviews = new HashSet<>() : customerReviews; }

    public void addAddress(Address o) {
        getAddresses().add(o);
        o.setCustomer(this);
    }

    public void removeAddress(Address o) {
        getAddresses().remove(o);
        o.setCustomer(null);
    }

    public void addShopOrder(ShopOrder o) {
        getShopOrders().add(o);
        o.setCustomer(this);
    }

    public void removeShopOrder(ShopOrder o) {
        getShopOrders().remove(o);
        o.setCustomer(null);
    }

    public void addCustomerReview(CustomerReview o) {
        getCustomerReviews().add(o);
        o.setCustomer(this);
    }

    public void removeShoppingCartItem(CustomerReview o) {
        getCustomerReviews().remove(o);
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

    public enum CustomerRoles {
        USER, MODER, ADMIN;
    }
}
