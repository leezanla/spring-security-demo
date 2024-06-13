/**
 * @author leezan
 * @Data 6/12/2024 8:25 PM
 */
package cc.lz.springsecuritysangeng.handler;

import cc.lz.springsecuritysangeng.entity.ResponseResult;
import cc.lz.springsecuritysangeng.utils.WebUtils;
import com.alibaba.fastjson2.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "权限不足");
        String jsonString = JSON.toJSONString(result);
        // 处理异常
        WebUtils.renderString(httpServletResponse, jsonString);
    }
}
