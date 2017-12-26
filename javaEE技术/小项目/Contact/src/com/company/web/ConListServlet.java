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
import java.util.List;

@WebServlet(name = "ConListServlet")
public class ConListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1)读取所有的联系人数据
        ContactDao dao = new ContactDaoImpl();
        List<Contact> conList = dao.findAll();
        //2)显示到浏览器
        response.setContentType("text/html;charset=utf-8");;
        String html = "";
        html += "<!DOCTYPE html>";
        html += "<html>";
        html += "<head>";
        html += "    <meta charset='UTF-8'>";
        html += "    <title>查询所有联系人</title>";
        html += "</head>";
        html += "<body>";
        html += "<center><h3>查询所有联系人</h3></center>";
        html += "<table border='1' align='center' width='700px'>";
        html += "    <tr>";
        html += "        <th>编号</th>";
        html += "        <th>姓名</th>";
        html += "        <th>性别</th>";
        html += "        <th>电话</th>";
        html += "        <th>邮箱</th>";
        html += "        <th>地址</th>";
        html += "        <th>操作</th>";
        html += "    </tr>";
        if(conList !=null){
            for(Contact contact : conList) {
                html += "    <tr>";
                html += "        <td>"+contact.getId()+"</td>";
                html += "        <td>"+contact.getName()+"</td>";
                html += "        <td>"+contact.getGender()+"</td>";
                html += "        <td>"+contact.getPhone()+"</td>";
                html += "        <td>"+contact.getEmail()+"</td>";
                html += "        <td>"+contact.getAddress()+"</td>";
                html += "        <td><a href='"+request.getContextPath()+"/ConQueryServlet?id="+contact.getId()+"'>修改</a>&nbsp;<a href='"+request.getContextPath()+"/ConDelServlet?id="+contact.getId()+"'>删除</a></td>";
                html += "    </tr>";
            }
        }
        html += "    <tr>";
        html += "        <td colspan='7' align='center'><a href='"+request.getContextPath()+"/addCon.html'>[添加联系人]</a> </td>";
        html += "    </tr>";
        html += "</table>";
        html += "</body>";
        html += "</html>";
        response.getWriter().write(html);
    }
}
