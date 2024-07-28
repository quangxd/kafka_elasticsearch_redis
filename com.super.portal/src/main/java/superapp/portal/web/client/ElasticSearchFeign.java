package superapp.portal.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import superapp.portal.web.model.AppStatus;

@FeignClient(value = "ElasticSearchFeign", url = "localhost:3333")
public interface ElasticSearchFeign {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/es/user-audit-trial/find-by-name")
    ResponseEntity<AppStatus> findAllByName(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/es/user-audit-trial/query")
    ResponseEntity<AppStatus> queryByCriteria(@RequestBody Object name);
}
