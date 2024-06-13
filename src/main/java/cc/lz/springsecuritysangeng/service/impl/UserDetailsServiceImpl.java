/**
 * @author leezan
 * @Data 5/31/2024 10:45 AM
 */
package cc.lz.springsecuritysangeng.service.impl;

import cc.lz.springsecuritysangeng.dao.MenuDao;
import cc.lz.springsecuritysangeng.dao.UserDao;
import cc.lz.springsecuritysangeng.entity.LoginUser;
import cc.lz.springsecuritysangeng.entity.User;
import cc.lz.springsecuritysangeng.exceptions.UserNotFoundException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserDao userDao;

    @Resource
    private MenuDao menuDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 查询用户信息
        User user = userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, s));
        if (Objects.isNull(user)) {
            throw new UserNotFoundException(404, "数据库中不存在" + s + "用户");
        }
        // 如果存在该用户，需要把用户封装成userdetails返回


        //TODO 查询对应的权限信息
//        List<String> list = new ArrayList<>(Arrays.asList("test", "admin"));
        List<String> list = menuDao.selectPermsById(user.getId());

        return new LoginUser(user, list);
    }
}

