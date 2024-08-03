package superapp.portal.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;

@SpringBootApplication
@EnableFeignClients
public class SuperPortalWebApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SuperPortalWebApplication.class, args);
    }

}
