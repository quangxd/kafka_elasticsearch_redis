package superapp.portal.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import superapp.portal.web.model.AppStatus;

import java.util.UUID;

@FeignClient(value = "RedisFeign", url = "localhost:3333")
public interface RedisFeign {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/redis/{id}/detail")
    ResponseEntity<AppStatus> getById(@PathVariable("id") UUID id);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/redis/users")
    ResponseEntity<AppStatus> getLast10Users();
}
