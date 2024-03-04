package org.electronicsstore.backend.dtos.product;

import java.time.LocalDateTime;
import java.util.Set;

public interface CategoryTraversedDownProj {
        Long getId();
        String getName();
        String getDescription();
        LocalDateTime getCreatedAt();
        Set<CategoryTraversedDownProj> getChildren();
        Set<ProductProj> getProducts();
        Set<CharProj> getProductChars();
}
