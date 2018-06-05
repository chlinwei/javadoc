package lw.pers.handlers;

import lw.pers.beans.Student;
import lw.pers.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;

//将springmvc该为注解
@org.springframework.stereotype.Controller
public class StudentController{
    @Autowired
    @Qualifier("studentService")
    private IStudentService service;

    public void setService(IStudentService service) {
        this.service = service;
    }

    @RequestMapping("/register.do")
    public String doRegister(String name,int age) throws Exception {
        Student student = new Student(name,age);
        service.addStudent(student);
        return "/welcome.jsp";
    }
}
