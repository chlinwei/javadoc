package lw.pers.framework;

import lw.pers.framework.bean.ActionMapping;
import lw.pers.framework.bean.ConfigurationManager;
import lw.pers.framework.bean.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

@WebServlet(name = "ActionServlet")
public class ActionServlet extends HttpServlet {
    private ConfigurationManager manager = new ConfigurationManager();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<String, ActionMapping> actionMappingMaps = manager.getActionMappingMaps();
            //1)获取用户的请求(login.action / register.action...)
            String requestURI = request.getRequestURI();//  /test/login.action
            String pathName = requestURI.substring(requestURI.lastIndexOf("/") + 1, requestURI.lastIndexOf("."));//login
            //2)根据用户的请求,创建不同的Action对象
            if (!actionMappingMaps.containsKey(pathName)) {
                //不存在对应的Action
                throw new RuntimeException("找不到对应的Action");
            }
            ActionMapping actionMapping = actionMappingMaps.get(pathName);
            //构造action对象
            String className = actionMapping.getClassName();
            Class clazz = Class.forName(className);
            Object actionObj = clazz.newInstance();

            //执行方法
            String methodName = actionMapping.getMethod();
            Method actionMethod = clazz.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            String resultFlag = (String)actionMethod.invoke(actionObj, request, response);

            //3)得到视图
            Map<String,Result> results = actionMapping.getResultMap() ;
            if(!results.containsKey(resultFlag)){
                throw new RuntimeException("找不到对应的Result视图");
            }
            Result result = results.get(resultFlag);

            //4)跳转视图
            //跳转类型
            String type = result.getType();
            //跳转页面
            String page = result.getPage();
            System.out.println("page:"+page);
            if(type.equals("dispatcher")){
                request.getRequestDispatcher(page).forward(request,response);
            }else{
                response.sendRedirect(request.getContextPath()+page);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        //3)调用Action对象里的方法
        //4)获取需要跳转的页面,然后进行跳转
    }
}