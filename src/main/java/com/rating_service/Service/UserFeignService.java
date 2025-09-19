package com.rating_service.Service;


import com.rating_service.DTO.APIResponse;
import com.rating_service.DTO.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "User-Service")
public interface UserFeignService {

    @GetMapping("/userService/user/getUserById/{id}")
    APIResponse<UserDto> getUserByUserId(@PathVariable Long id);
}
