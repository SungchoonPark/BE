package com.group.commitapp.user.repository;

import com.group.commitapp.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByName(String name);
  Optional<User> findByProviderId(String providerId);
}
