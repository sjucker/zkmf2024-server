package ch.zkmf2024.server.rest.secured;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class SecuredEndpoint {

    @GetMapping("/foo")
    public String foo() {
        return "bar";
    }
}
