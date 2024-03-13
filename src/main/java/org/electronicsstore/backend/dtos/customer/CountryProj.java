package org.electronicsstore.backend.dtos.customer;

import java.time.LocalDateTime;

public interface CountryProj {
    Long getId();
    String getCountryName();
    LocalDateTime getCreatedAt();
    LocalDateTime getModifiedAt();
}
