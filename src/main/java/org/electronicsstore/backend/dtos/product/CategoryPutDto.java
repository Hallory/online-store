package org.electronicsstore.backend.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPutDto {
    private Long id;
    @NotBlank
    @Size(min = 3, max = 30)
    private String name;
    private String description;
    private CategoryPutDto parent;
    private CategoryPutDto children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryPutDto that = (CategoryPutDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
