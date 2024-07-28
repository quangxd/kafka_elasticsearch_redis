package superapp.portal.web.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import superapp.portal.web.client.ElasticSearchFeign;
import superapp.portal.web.client.RedisFeign;
import superapp.portal.web.model.AppStatus;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/writer")
@RequiredArgsConstructor
@Slf4j
public class IReaderController {
    private final ElasticSearchFeign elasticSearchFeign;
    private final RedisFeign redisFeign;

    //ElasticSearch integration API
    @GetMapping("/es/user-audit-trial/find-by-name")
    public ResponseEntity<AppStatus> findAllByName(@RequestParam("name") String name) {
        log.info("START IReaderController.findAllByName with param: {}", name);

        return elasticSearchFeign.findAllByName(name);
    }


    @PostMapping("/es/user-audit-trial/query")
    public ResponseEntity<AppStatus> queryByCriteria(@RequestBody Object o) {
        log.info("START IReaderController.queryByCriteria with param: {}", o);

        return elasticSearchFeign.queryByCriteria(o);
    }

    //Redis integration API
    @GetMapping("/redis/{id}/detail")
    public ResponseEntity<AppStatus> getById(@PathVariable("id") UUID id) {
        log.info("START IReaderController.getById with param: {}", id);

        return redisFeign.getById(id);
    }

    @GetMapping("/redis/users")
    public ResponseEntity<AppStatus> getLast10Users() {
        log.info("START IReaderController.getLast10Users");

        return redisFeign.getLast10Users();
    }


}
