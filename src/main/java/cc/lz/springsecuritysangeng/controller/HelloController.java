/**
 * @author leezan
 * @Data 5/26/2024 9:12 PM
 */
package cc.lz.springsecuritysangeng.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("@ex.hasAuthority('system:depth.list')")
    public String hello() {
        return "hello";
    }
}
