/**
 * @author leezan
 * @Data 6/12/2024 5:23 PM
 */
package cc.lz.springsecuritysangeng.dao;

import cc.lz.springsecuritysangeng.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuDao extends BaseMapper<Menu> {
    List<String> selectPermsById(@Param("userId") Long userId);
}
