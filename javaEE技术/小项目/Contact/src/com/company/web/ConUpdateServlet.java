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

@WebServlet(name = "ConUpdateServlet")
public class ConUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.接受表单的数据
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        //2.把数据保存到xml中
        ContactDao dao = new ContactDaoImpl();
        Contact contact = new Contact();
        contact.setId(id);
        contact.setName(name);
        contact.setGender(gender);
        contact.setPhone(phone);
        contact.setEmail(email);
        contact.setAddress(address);
        dao.updateContact(contact);
        //3.跳转到查询所有联系人页面
        response.sendRedirect(request.getContextPath()+"/ConListServlet");
    }
}

