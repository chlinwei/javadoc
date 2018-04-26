package lw.pers.web;

import lw.pers.entity.User;
import lw.pers.framework.Action;
import lw.pers.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理登陆请求的代码逻辑
 */
public class LoginAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("utf-8");
        //1）获取用户参数
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        //封装成javabean对象
        User user = new User() ;
        user.setName(name);
        user.setPassword(password);

        //2)调用业务方法
        UserService service = new UserService();
        User loginUser = service.login(user);
        //返回值根据mystruts.xml文件的name属性
        if(loginUser==null){
            //没有此用户,登陆不成功,返回视图地址
            return "input";
        }else{
            //登陆成功,返回视图地址
            return "success";
        }
    }

}



