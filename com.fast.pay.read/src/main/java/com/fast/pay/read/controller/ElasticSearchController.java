package com.fast.pay.read.controller;

import com.fast.pay.read.model.AppStatus;
import com.fast.pay.read.service.UserAuditTrailSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/es")
@RequiredArgsConstructor
@Slf4j
public class ElasticSearchController {
    private final UserAuditTrailSearchService auditTrailSearchService;

    @GetMapping("/user-audit-trial/find-by-name")
    public ResponseEntity<AppStatus> findAllByName(@RequestParam("name") String name) {
        log.info("START ElasticSearchController.findAllByName with param: {}", name);

        AppStatus appStatus = AppStatus.of200(
                auditTrailSearchService.findAllByName(name).getContent()
        );
        return ResponseEntity.ok(appStatus);
    }


    @PostMapping("/user-audit-trial/query")
    public ResponseEntity<AppStatus> queryByCriteria(@RequestBody Object o)  {
        log.info("START IReaderController.queryByCriteria with param: {}", o);

        AppStatus appStatus = AppStatus.of200(
                auditTrailSearchService.queryByCriteria(o)
        );

        return ResponseEntity.ok(appStatus);
    }


}
