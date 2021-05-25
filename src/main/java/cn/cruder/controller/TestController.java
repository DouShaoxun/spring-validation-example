package cn.cruder.controller;

import cn.cruder.dto.Dog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Cruder
 * @Date 2021/5/25 0025 22:22
 */
@Slf4j
@RestController
@RequestMapping("/va")
public class TestController {

    @PostMapping("/test1")
    public Object test1(@RequestBody @Validated Dog dog) {
        log.info(dog.toString());
        return dog;
    }
}
