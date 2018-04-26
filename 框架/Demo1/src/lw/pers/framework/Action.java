package lw.pers.framework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 所有业务请求标准的接口
 */
public interface Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException;
}
