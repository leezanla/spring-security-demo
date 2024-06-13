package cc.lz.springsecuritysangeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cc.lz.springsecuritysangeng.dao")
public class SpringSecuritySangengApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecuritySangengApplication.class, args);
    }

}
