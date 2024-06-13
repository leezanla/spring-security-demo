/**
 * @author leezan
 * @Data 5/31/2024 10:33 AM
 */
package cc.lz.springsecuritysangeng.service.impl;

import cc.lz.springsecuritysangeng.dao.UserDao;
import cc.lz.springsecuritysangeng.entity.LoginUser;
import cc.lz.springsecuritysangeng.entity.ResponseResult;
import cc.lz.springsecuritysangeng.entity.User;
import cc.lz.springsecuritysangeng.service.UserService;
import cc.lz.springsecuritysangeng.utils.JWTUtils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(User user) {
        // AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(passwordAuthenticationToken);
        // 如果认证没有通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            new Exception("用户名或密码错误");
        }
        // 如果认证通过了，使用userid生成一个jwt
        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        Long id = principal.getUser().getId();
        String token = JWTUtils.generateToken(JSON.toJSONString(principal ));
        System.out.println(token);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        // 把完整的用户信息存入redis
        stringRedisTemplate.opsForValue().set("token:"+id, token, 1, TimeUnit.HOURS);
        return new ResponseResult(200, map);
    }

    @Override
    public ResponseResult logout() {
        // 获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        // 删除redis中的值
        stringRedisTemplate.delete("token:"+userId);
        return new ResponseResult(200,"退出成功");
    }
}
