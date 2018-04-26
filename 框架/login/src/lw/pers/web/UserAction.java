package lw.pers.web;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import lw.pers.entity.User;

import java.util.Map;

public class UserAction extends ActionSupport{
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String login()throws Exception{
        if("eric".equals(user.getName())&&"1234".equals(user.getPassword())){
            //登陆成功
            //1)保存数据到session域中
            ActionContext context = ActionContext.getContext();
            Map<String, Object> sessionMap = context.getSession();
            sessionMap.put("user",user);
            //2)跳转到用户主页
            return SUCCESS;
        }else{
            //登陆失败
            return ERROR;
        }
    }
    public String resiter()throws Exception{
        System.out.println("注册");
        return "login";
    }
}
