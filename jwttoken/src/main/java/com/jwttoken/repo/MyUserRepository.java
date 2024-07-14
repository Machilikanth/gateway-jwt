package com.jwttoken.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jwttoken.model.MyUser;

@Repository
public interface MyUserRepository  extends MongoRepository<MyUser, Long> {

	Optional<MyUser> findByUsername(String username);
}
