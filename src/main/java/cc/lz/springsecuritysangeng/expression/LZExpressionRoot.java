/**
 * @author leezan
 * @Data 6/12/2024 9:10 PM
 */
package cc.lz.springsecuritysangeng.expression;

import cc.lz.springsecuritysangeng.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ex")
public class LZExpressionRoot {
    public boolean hasAuthority(String authority) {
        // 获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        // 判断用户权限集合当中是否存在authority
        return permissions.contains(authority);
    }
}
