package com.appsdeveloper.app.ws.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloper.app.ws.entity.request.UpdateUserRequest;
import com.appsdeveloper.app.ws.entity.request.UserRequest;
import com.appsdeveloper.app.ws.entity.response.UserResponse;
import com.appsdeveloper.app.ws.exceptions.UserServiceException;
import com.appsdeveloper.app.ws.service.USerServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

	Map<String, UserResponse> userMap;
	
	@Autowired
	USerServiceImpl userService;

	@GetMapping()
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="limit", defaultValue="50") int limit,
			@RequestParam(value="sort", defaultValue="dec", required=false) String sort) {
		return "getUsers was called with page=" + page + " and limit=" + limit + " and sort=" + sort;
	}

	@GetMapping(path="/{userId}", produces = {MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponse> getUser(@PathVariable String userId) {
		
//		if (true) {
//			throw new UserServiceException("A USer Service Exception is thrown...");
//		}

//		if(userMap.containsKey(userId)) {
//			return new ResponseEntity<UserResponse>(userMap.get(userId), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<UserResponse>(HttpStatus.NO_CONTENT);
//		}
		UserResponse userResponse = userService.getUser(userId);
		if(userResponse != null) {
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		} else {
			throw new UserServiceException("A User with ID: " + userId + " cannot be found.");
		}

	}

	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
//		String userId = UUID.randomUUID().toString();
//
//		UserResponse userResponse = new UserResponse();
//		userResponse.setFirstName(userRequest.getFirstName());
//		userResponse.setLastName(userRequest.getLastName());
//		userResponse.setEmail(userRequest.getEmail());
//		userResponse.setUserId(userId);
//
//		if (userMap == null) {userMap = new HashMap<>();}
//		userMap.put(userId, userResponse);

		UserResponse userResponse = userService.createUser(userRequest);
		
		if(userResponse != null) {
			return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResponse>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping(path="/{userId}", consumes = {MediaType.APPLICATION_XML_VALUE,
											  MediaType.APPLICATION_JSON_VALUE},
								  produces = {MediaType.APPLICATION_XML_VALUE,
											  MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponse> updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserRequest userRequest) {
		UserResponse userResponse;
		if(userMap.containsKey(userId)) {
			userResponse = userMap.get(userId);
		} else {
			return new ResponseEntity<UserResponse>(HttpStatus.NO_CONTENT);
		}
		
		userResponse.setFirstName(userRequest.getFirstName());
		userResponse.setLastName(userRequest.getLastName());
		userMap.put(userId, userResponse);
		
		//return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		return new ResponseEntity<UserResponse>(userMap.get(userId), HttpStatus.OK);
	}

	@DeleteMapping(path="/{userId}")
	public ResponseEntity<Void> deleteUSer(@PathVariable String userId) {
		if(userMap.containsKey(userId)) {
			userMap.remove(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
