package com.company.web;

import com.company.dao.ContactDao;
import com.company.dao.impl.ContactDaoImpl;
import com.company.entity.Contact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConQueryServlet")
public class ConQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1)接受需要修改的id
        String id = request.getParameter("id");
        //2)查询对应的联系人
        ContactDao dao = new ContactDaoImpl();
        Contact contact = contact = dao.findById(id);

        //3)显示到浏览器
        response.setContentType("text/html;charset=utf-8");
        String html = "";
        html +="<html>";
        html +="<head>";
        html +="    <meta charset='utf-8'>";
        html +="</head>";
        html +="<body>";
        html +="<center><h3>修改联系人</h3></center>";
        html +="<form action='"+request.getContextPath()+"/ConUpdateServlet' method='post'>";
        //使用隐藏域,用户传递参数,这个参数用户可以不用看见
        html += "<input type='hidden' name='id' value='"+contact.getId()+"'/>";
        html +="    <table border='1' align='center' width='300px'>";
        html +="        <tr>";
        html +="            <th>姓名</th>";
        html +="            <td><input type='text' name='name' value='"+contact.getName()+"'></td>";
        html +="        </tr>";
        html +="        <tr>";
        html +="            <th>性别</th>";
        if(contact.getGender().equals("男")) {
            html += "            <td><input type='radio' name='gender' value='男' checked='checked'>男";
            html += "                <input type='radio' name='gender' value='女'>女</td>";
        }else{
            html += "            <td><input type='radio' name='gender' value='男'>男";
            html += "                <input type='radio' name='gender' value='女' checked='checked'>女</td>";
        }
        html +="        </tr>";
        html +="        <tr>";
        html +="            <th>电话</th>";
        html +="            <td><input type='text' name='phone' value='"+contact.getPhone()+"'></td>";
        html +="        </tr>";
        html +="        <tr>";
        html +="            <th>邮箱</th>";
        html +="            <td><input type='text' name='email' value='"+contact.getEmail()+"'></td>";
        html +="        </tr>";
        html +="        <tr>";
        html +="            <th>地址</th>";
        html +="            <td><input type='text' name='address' value='"+contact.getAddress()+"'></td>";
        html +="        </tr>";
        html +="        <tr>";
        html +="            <td colspan='2' align='center'><input type='submit' value='修改'></td>";
        html +="        </tr>";
        html +="    </table>";
        html +="</form>";
        html +="</body>";
        html +="</html>";
        response.getWriter().write(html);
    }
}
