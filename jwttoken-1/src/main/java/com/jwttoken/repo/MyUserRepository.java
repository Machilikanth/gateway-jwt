package com.jwttoken.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.jwttoken.model.MyUser;

@Repository
public interface MyUserRepository  extends MongoRepository<MyUser, String> {
//	Optional<MyUser> findByUsername(String username);
	Optional<MyUser> findByMobileNumber(String mobileNumber);
}
