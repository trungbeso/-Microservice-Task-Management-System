package com.taskmanagement.sevice;

import com.taskmanagement.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-SERVICE", url = "http://localhost:8081")
public interface IUserService {

	@GetMapping("api/v1/users/profile")
	UserDto getUserProfile (@RequestHeader("Authorization") String jwt);


}
