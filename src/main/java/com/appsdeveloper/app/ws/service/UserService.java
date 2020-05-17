package com.appsdeveloper.app.ws.service;

import com.appsdeveloper.app.ws.entity.request.UserRequest;
import com.appsdeveloper.app.ws.entity.response.UserResponse;

public interface UserService {
	
	UserResponse createUser(UserRequest userRequest);
	UserResponse getUser(String userId);

}
