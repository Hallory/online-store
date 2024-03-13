package org.electronicsstore.backend.dtos.order;

import java.time.LocalDateTime;

public interface ShippingMethodProj {
    Long getId();
    String getName();
    String getPrice();
    LocalDateTime getCreatedAt();
    LocalDateTime getModifiedAt();
}
