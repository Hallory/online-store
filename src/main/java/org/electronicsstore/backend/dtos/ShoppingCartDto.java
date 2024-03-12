package org.electronicsstore.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShoppingCartDto {
    private String id;
    private Double total;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedA;
}
