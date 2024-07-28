package com.fast.pay.read.repository;

import com.fast.pay.read.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserService extends JpaRepository<User, UUID> {
}
