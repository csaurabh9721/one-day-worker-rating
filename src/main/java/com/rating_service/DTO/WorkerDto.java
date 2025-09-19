package com.rating_service.DTO;

import lombok.Data;

@Data
public class WorkerDto {
    private Long id;
    private String name;
    private Integer age;
    private String photo;
    private String mobile;
    private AddressDto address;
}
