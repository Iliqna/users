package com.users.repository;

import com.users.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserById(Long id);

    List<User> findUserByFullNameIgnoreCase(String fullName);
}
