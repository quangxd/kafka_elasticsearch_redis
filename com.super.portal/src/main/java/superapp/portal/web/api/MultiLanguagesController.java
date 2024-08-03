package superapp.portal.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import superapp.portal.web.multilanguages.I18n;

@RestController
@RequestMapping("/api/v1/i18n")
@Slf4j
@RequiredArgsConstructor
public class MultiLanguagesController {
    @GetMapping("/greeting")
    public ResponseEntity<String> greetingEN(@RequestParam(value = "lan", required = false) String lan) throws Exception {
        return ResponseEntity.ok(I18n.msg("greet", lan));
    }

}
