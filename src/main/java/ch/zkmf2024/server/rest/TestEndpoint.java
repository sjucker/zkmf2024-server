package ch.zkmf2024.server.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ch.zkmf2024.server.service.DateUtil.now;

@Slf4j
@RestController
@RequestMapping("/public/test")
public class TestEndpoint {

    @GetMapping
    public String test() {
        log.error("test %s".formatted(now()));
        return "test";
    }

}
