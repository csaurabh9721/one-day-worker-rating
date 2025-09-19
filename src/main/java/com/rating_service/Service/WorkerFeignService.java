package com.rating_service.Service;

import com.rating_service.DTO.APIResponse;
import com.rating_service.DTO.WorkerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Worker-Service")
public interface WorkerFeignService {
    @GetMapping("/workerService/worker/getWorkerById/{id}")
    APIResponse<WorkerDto> getWorkerByUserId(@PathVariable Long id);
}
