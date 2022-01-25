package com.mk.backend.assignement.banking.repositories.user;

import com.mk.backend.assignement.banking.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User , Long> {


    Optional<User> findByUsername(String username);
}
