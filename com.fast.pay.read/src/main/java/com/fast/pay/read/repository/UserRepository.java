package com.fast.pay.read.repository;

import com.fast.pay.read.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUuid(UUID uuid);
    List<User> findAll();
}
