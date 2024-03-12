package org.electronicsstore.backend.dtos.customer;

import java.time.LocalDateTime;

public interface ReviewProj {
    String getId();
    String getText();
    Double getRating();
    LocalDateTime getCreatedAt();
    LocalDateTime getModifiedAt();
    LocalDateTime getDeletedAt();
}
