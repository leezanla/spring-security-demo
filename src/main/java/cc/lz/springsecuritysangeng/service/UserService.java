/**
 * @author leezan
 * @Data 5/31/2024 10:33 AM
 */
package cc.lz.springsecuritysangeng.service;

import cc.lz.springsecuritysangeng.entity.ResponseResult;
import cc.lz.springsecuritysangeng.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    ResponseResult login(User user);

    ResponseResult logout();
}
