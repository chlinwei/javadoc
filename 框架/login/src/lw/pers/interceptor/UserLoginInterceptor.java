package lw.pers.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;
import lw.pers.entity.User;

import java.util.Map;

/**
 * 目标:拦截用户的权限
 */
public class UserLoginInterceptor implements Interceptor {
    @Override
    public void destroy() {
    }
    @Override
    public void init() {
    }

    //前提:假设所有的请求都被这个拦截器拦截
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        /**
         * 问题:该拦截器不需要拦截登陆(user_login)和注册(user_register)请求
         */
        //得到当前拦截的目标Action代理对象
        ActionProxy actionProxy = invocation.getProxy();
        //得到当地钱执行的action的方法
        String methodName = actionProxy.getMethod();
        if("login".equals(methodName)||"register".equals(methodName)){
            //放行
            return invocation.invoke();
        }
        //1)获取session域的数据
        ActionContext context = ActionContext.getContext();
        Map<String, Object> sessionMap = context.getSession();
        User user = (User)sessionMap.get("user");
        //2)判断session域的数据是否存在
        if(user==null){
            //3)如果不存在,则跳转到登陆
            return "login";
        }else {
            //4)如果存在,则放行
            return invocation.invoke();
        }
    }
}

