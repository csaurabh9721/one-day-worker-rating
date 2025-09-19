package com.rating_service.DTO;


import lombok.Data;

@Data
class AddressDto {
    private Long id;

    private String locality;

    private String subLocality;

    private String city;

    private String state;

    private String country;
    private Integer pinCode;
}