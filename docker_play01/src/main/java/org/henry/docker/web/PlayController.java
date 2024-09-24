package org.henry.docker.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author henry
 * @date 2024/5/23 10:40
 */
@RestController
public class PlayController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/play/v1")
    public String playV1() {
        return "Hello World" + "\t" + port + "\t" + System.currentTimeMillis();
    }

    @GetMapping("/play/v2")
    public String playV2() {
        return "端口号" + "\t" + port + "\t" + System.currentTimeMillis();
    }
}
