package org.electronicsstore.backend.dtos.product;

import java.time.LocalDateTime;
import java.util.Set;

public interface CategoryTraversedDownProj {
        Long getId();
        String getName();
        String getDescription();
        LocalDateTime getCreatedAt();
        Set<CategoryTraversedDownProj> getChildren();
        Set<ProductProjEmb> getProducts();
        Set<CharProjEmb> getProductChars();

        interface ProductProjEmb {
                String getId();
                String getArticle();
                String getBarcode();
                String getName();
                String getSKU();
                String getBrand();
                Double getPrice();
                String getDescription();
                Integer getQtyInStock();
                Set<String> getProductImages();
                String getProductIcon();
                LocalDateTime getCreatedAt();
                LocalDateTime getDeletedAt();
        }

        interface CharProjEmb {
                Long getId();
                String getName();
                String getDataType();
        }
}
