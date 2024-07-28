package com.fast.pay.read.repository;

import com.fast.pay.read.entity.UserAuditTrail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface UserAuditTrialRepository extends ElasticsearchRepository<UserAuditTrail, UUID> {

    Page<UserAuditTrail> findAllByName(String name, Pageable pageable);
}
