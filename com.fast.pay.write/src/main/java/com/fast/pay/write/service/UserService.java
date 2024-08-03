package com.fast.pay.write.service;

import com.fast.pay.write.entity.User;
import com.fast.pay.write.entity.UserAuditTrail;
import com.fast.pay.write.repository.UserAuditTrialRepository;
import com.fast.pay.write.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final UserRedisWriteService userRedisService;
    private final UserAuditTrialRepository userAuditTrialRepository;

    @KafkaListener(topics = "create-user", groupId = "writer")
    public User insert(Object o) throws IOException {

        ConsumerRecord<String, Object> record = (ConsumerRecord) o;
        log.info("Received message: {}", record);

        User user = objectMapper.readValue(record.value().toString(), User.class);

        if (user.getUuid() == null) {
            user.setUuid(UUID.randomUUID());
            user.setActionType("INSERTED");
            userRepository.save(user);

            //save audit trial to ElasticSearch
            UserAuditTrail userAuditTrail = UserAuditTrail.builder().id(UUID.randomUUID()).userId(user.getUuid())
                    .age(user.getAge()).name(user.getName()).address(user.getAddress()).actionType("INSERTED")
                    .createdAt(LocalDateTime.now().toString()).build();

            log.info("ElasticSearch START insert document: {}", userAuditTrail);
            userAuditTrialRepository.save(userAuditTrail);

            //Redis cache
            log.info("Redis START put document: {}", user);
            userRedisService.put(user);
            //delete List User by wildcard key
            log.info("Redis START delete all documents match wildcard: {}", "all_user:*");
            userRedisService.delete("all_user:*");

            //update newest data on Redis
            log.info("Redis START update all documents to Redis");
//            userRedisService.putAll(
//                    userRepository.findAll()
//            );
        }

        return user;
    }

    @KafkaListener(topics = "update-user", groupId = "writer")
    public User update(Object o) throws Exception {

        ConsumerRecord<String, Object> record = (ConsumerRecord) o;
        log.info("Received message: {}", record);

        User user = objectMapper.readValue(record.value().toString(), User.class);

        if (user.getUuid() == null) {
            throw new Exception("ID cannot be null");
        }

        User userToUpdate = userRepository.findById(user.getUuid()).orElseThrow();

        //save audit trial to ElasticSearch
        UserAuditTrail userAuditTrail = UserAuditTrail.builder().id(UUID.randomUUID()).userId(user.getUuid())
                .age(user.getAge()).name(user.getName()).address(user.getAddress()).actionType("UPDATED")
                .createdAt(LocalDateTime.now().toString()).build();

        log.info("ElasticSearch START insert document: {}", userAuditTrail);
        userAuditTrialRepository.save(userAuditTrail);

        userToUpdate.setUuid(user.getUuid());
        userToUpdate.setName(user.getName());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setAddress(user.getName());
        userToUpdate.setActionType("UPDATED");

        userRepository.save(userToUpdate);

        //update Redis cache
        log.info("Redis START put document: {}", userToUpdate);
        userRedisService.put(userToUpdate);
        //delete List User by wildcard key
        log.info("Redis START delete all documents match wildcard: {}", "all_user:*");
        userRedisService.delete("all_user:*");

        //update newest data on Redis
        log.info("Redis START update all documents to Redis");
        userRedisService.putAll(
                userRepository.findAll()
        );

        return user;
    }


}
