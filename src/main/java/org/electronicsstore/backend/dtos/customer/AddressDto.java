package org.electronicsstore.backend.dtos.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;
    private String unitNum;
    private String streetNum;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String region;
    private String postalCode;
    private Boolean isDefault;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime modifiedAt;
    private CountryEmbDto countryId;

    @Data
    public static class CountryEmbDto {
        private Long id;
    }
}
