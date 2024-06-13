/**
 * @author leezan
 * @Data 5/31/2024 4:18 PM
 */
package cc.lz.springsecuritysangeng.controller;

import cc.lz.springsecuritysangeng.entity.ResponseResult;
import cc.lz.springsecuritysangeng.entity.User;
import cc.lz.springsecuritysangeng.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("/user/logout")
    public ResponseResult logout() {
        return userService.logout();
    }
}
