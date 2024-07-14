package com.jwttoken.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "JWT_Token1")
public class MyUser {
	@Id
	private String userId;
	private String username;
	private String mobileNumber;
	private String email;
}
