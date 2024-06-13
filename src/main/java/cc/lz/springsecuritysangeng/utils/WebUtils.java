/**
 * @author leezan
 * @Data 5/26/2024 11:48 PM
 */
package cc.lz.springsecuritysangeng.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {
    public static String renderString(HttpServletResponse response, String string) {
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().print(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
