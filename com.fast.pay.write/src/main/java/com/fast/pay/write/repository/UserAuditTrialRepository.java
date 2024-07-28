package com.fast.pay.write.repository;

import com.fast.pay.write.entity.UserAuditTrail;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface UserAuditTrialRepository extends ElasticsearchRepository<UserAuditTrail, UUID> {
}
