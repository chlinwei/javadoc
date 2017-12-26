package com.company.web;

import com.company.dao.ContactDao;
import com.company.dao.impl.ContactDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConDelServlet")
public class ConDelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1)接受需要删除的id
        String id = request.getParameter("id");
        //2)在xml文件中删除联系人
        ContactDao dao = new ContactDaoImpl();
        dao.deleteContact(id);

        //3)跳转到查询所有联系人页面
        response.sendRedirect(request.getContextPath()+"/ConListServlet");
    }
}
