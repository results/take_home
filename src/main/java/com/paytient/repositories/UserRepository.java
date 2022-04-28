package com.paytient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paytient.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
