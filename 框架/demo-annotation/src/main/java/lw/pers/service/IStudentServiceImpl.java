package lw.pers.service;

import lw.pers.beans.Student;
import lw.pers.dao.IStudentDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

//spring IOC 注解
@Service("studentService")
public class IStudentServiceImpl implements IStudentService {
    //或者Autowired,Qualifier
    @Resource(name="IStudentDao")
    private IStudentDao dao;
    public void setDao(IStudentDao dao) {
        this.dao = dao;
    }

    //spring aop 注解
    @Transactional
    @Override
    public void addStudent(Student student) {
        dao.insertStudent(student);
    }
}
