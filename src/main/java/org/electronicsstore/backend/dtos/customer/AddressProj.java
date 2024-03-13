package org.electronicsstore.backend.dtos.customer;

import java.time.LocalDateTime;

public interface AddressProj {
    String getId();
    String getUnitNum();
    String getStreetNum();
    String getAddressLine1();
    String getAddressLine2();
    String getCity();
    String getRegion();
    String getPostalCode();
    Boolean getIsDefault();
    LocalDateTime getCreatedAt();
    LocalDateTime getModifiedAt();
    CustomerProjEmb getCustomer();
    CountryProj getCountry();

    interface CustomerProjEmb {
        String getId();
        String getAccountId();
        String getUsername();
        String getFirstName();
        String getLastName();
        String getMiddleName();
        String getEmail();
        String getPhoneNum();
        LocalDateTime getCreatedAt();
        LocalDateTime getModifiedAt();
        LocalDateTime getDeletedAt();
        Boolean getIsDeleted();
    }
}
