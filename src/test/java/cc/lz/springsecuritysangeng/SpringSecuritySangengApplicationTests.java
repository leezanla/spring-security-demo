package cc.lz.springsecuritysangeng;

import cc.lz.springsecuritysangeng.dao.MenuDao;
import cc.lz.springsecuritysangeng.entity.User;
import cc.lz.springsecuritysangeng.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringSecuritySangengApplicationTests {

    @Resource
    private UserService userService;

    @Resource
    private MenuDao menuDao;

    @Test
    void contextLoads() {
        System.out.println(userService.getOne(new QueryWrapper<User>().eq("id", 1)));
    }

    @Test
    void testPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("rotqiujc");
        System.out.println(encode);
        System.out.println(passwordEncoder.matches("123", "$2a$10$YDo2mLSKDe0BoZ0G/FHGUeIlOiUt2FYFkTmszoYcim9RVrPe7IKRK"));
    }

    @Test
    void testMenuDao() {
        List<String> strings = menuDao.selectPermsById(1L);
        strings.stream().forEach(System.out::println);
    }

}
