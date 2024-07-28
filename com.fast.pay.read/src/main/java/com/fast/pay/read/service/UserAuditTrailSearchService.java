package com.fast.pay.read.service;

import com.fast.pay.read.entity.UserAuditTrail;
import com.fast.pay.read.model.UserAuditTrialQueryDTO;
import com.fast.pay.read.repository.UserAuditTrialRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAuditTrailSearchService {
    private static final String USER_AUDIT_TRIAL_INDEX = "user_audit_trial";
    private final ElasticsearchOperations elasticsearchOperations;
    private final ObjectMapper objectMapper;
    private final UserAuditTrialRepository auditTrialRepository;

    public Page<UserAuditTrail> findAllByName(String name) {
        return auditTrialRepository.findAllByName(name, PageRequest.of(0, 10));
    }

    public List<UserAuditTrail> queryByCriteria(Object o) {
        UserAuditTrialQueryDTO queryDTO = objectMapper.convertValue(
                o,
                new TypeReference<UserAuditTrialQueryDTO>(){}
        );

        //Search with criteria
        Criteria criteria = new Criteria("name").is(queryDTO.getName())
                .and("age").is(queryDTO.getAge());

        Query searchQuery = new CriteriaQuery(criteria);
        SearchHits<UserAuditTrail> userAuditTrailSearchHits =
                elasticsearchOperations
                        .search(searchQuery,
                                UserAuditTrail.class,
                                IndexCoordinates.of(USER_AUDIT_TRIAL_INDEX));

        return userAuditTrailSearchHits.getSearchHits().stream().map(
                e -> e.getContent()
        ).collect(Collectors.toList());
    }
}
