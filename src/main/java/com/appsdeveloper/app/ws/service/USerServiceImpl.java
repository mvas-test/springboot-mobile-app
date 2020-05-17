package com.appsdeveloper.app.ws.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloper.app.ws.entity.request.UserRequest;
import com.appsdeveloper.app.ws.entity.response.UserResponse;
import com.appsdeveloper.app.ws.shared.Utils;

@Service
public class USerServiceImpl implements UserService{
	
	Map<String, UserResponse> userMap;
	Utils utils;
	
	@Autowired
	public USerServiceImpl(Utils utils) {
		super();
		this.utils = utils;
	}

	@Override
	public UserResponse createUser(UserRequest userRequest) {
		String userId = utils.generateUserId();

		UserResponse userResponse = new UserResponse();
		userResponse.setFirstName(userRequest.getFirstName());
		userResponse.setLastName(userRequest.getLastName());
		userResponse.setEmail(userRequest.getEmail());
		userResponse.setUserId(userId);

		if (userMap == null) {userMap = new HashMap<>();}
		userMap.put(userId, userResponse);
		
		return userResponse;
	}

	@Override
	public UserResponse getUser(String userId) {
		if(userMap.containsKey(userId)) {
			return userMap.get(userId);
		} else {
			return null;
		}
	}
	

}
