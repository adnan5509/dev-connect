package com.aab.dev_connect.repository;

import com.aab.dev_connect.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
