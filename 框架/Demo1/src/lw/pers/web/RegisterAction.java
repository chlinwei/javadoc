package lw.pers.web;

import lw.pers.entity.User;
import lw.pers.framework.Action;
import lw.pers.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1)获取用户参数
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        //封装javabean对象
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        //2)调用业务方法
        UserService service = new UserService();
        service.register(user);
        //3)导航视图
        return "success";
    }
}
