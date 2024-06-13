/**
 * @author leezan
 * @Data 6/1/2024 3:30 PM
 */
package cc.lz.springsecuritysangeng.filter;

import cc.lz.springsecuritysangeng.entity.LoginUser;
import cc.lz.springsecuritysangeng.utils.JWTUtils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import io.jsonwebtoken.Claims;
import io.netty.util.internal.NativeLibraryLoader;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = httpServletRequest.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        // 解析token
        Claims claims = JWTUtils.parseToken(token);
        String subject = claims.getSubject();
        System.out.println(subject);
        LoginUser loginUser = JSON.parseObject(subject, new TypeReference<LoginUser>() {
            @Override
            public LoginUser parseObject(String text) {
                return super.parseObject(text);
            }
        });

        // 从redis中获取用户信息
        String s = stringRedisTemplate.opsForValue().get("token" + loginUser.getUser().getId());
        if (Objects.isNull(s)) {
            throw new RuntimeException("用户未登陆");
        }
        // 存放SecurityContextHolder中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getUsername(), null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
