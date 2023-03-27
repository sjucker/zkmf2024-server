package ch.zkmf2024.server.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/public/test")
public class TestEndpoint {

    @GetMapping
    public String test() {
        log.error("test");
        return "test";
    }

}
