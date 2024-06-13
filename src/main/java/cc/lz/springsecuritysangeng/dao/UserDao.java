/**
 * @author leezan
 * @Data 5/31/2024 10:32 AM
 */
package cc.lz.springsecuritysangeng.dao;

import cc.lz.springsecuritysangeng.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
