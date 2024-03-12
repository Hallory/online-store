package org.electronicsstore.backend.dtos.customer;

import java.time.LocalDateTime;

public interface ShoppingCartProj {
    String getId();
    Double getTotal();
    LocalDateTime getCreatedAt();
    LocalDateTime getModifiedAt();
}
