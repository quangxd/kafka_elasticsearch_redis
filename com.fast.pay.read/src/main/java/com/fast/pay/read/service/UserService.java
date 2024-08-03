package com.fast.pay.read.service;

import com.fast.pay.read.entity.User;
import com.fast.pay.read.model.AppStatus;
import com.fast.pay.read.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRedisReadService userRedisReadService;
    private final UserRepository userRepository;

    public AppStatus getById(UUID id) {
        User user = userRedisReadService.getById(id);
        if(null == user) {
            log.info("START calling Database to get user with id: {}", id);
            return AppStatus.of200(userRepository.findByUuid(id));
        }
        return AppStatus.of200(user);
    }

    public AppStatus getAll() {
        List<User> users = userRedisReadService.getAll();
        if(null == users) {
            log.info("START calling Database to get user list");
            return AppStatus.of200(userRepository.findAll());
        }
        return AppStatus.of200(users);
    }
}
