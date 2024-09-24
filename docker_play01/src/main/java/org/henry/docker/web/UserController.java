package org.henry.docker.web;

import java.util.Date;

import javax.annotation.Resource;

import org.henry.docker.db.entity.TUser;
import org.henry.docker.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author henry
 * @date 2024/5/24 11:58
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("user/get")
    public TUser getUser(Integer id) {
        return userService.findUser(id);
    }

    @PostMapping("user/create")
    public String createUser(String username, String password, Byte sex) {
        TUser user = new TUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(sex);
        user.setStatus((byte) 101);
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        userService.create(user);
        return "ok";
    }
}
