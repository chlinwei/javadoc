package lw.pers.service;

import lw.pers.beans.Student;
import lw.pers.dao.IStudentDao;

public class IStudentServiceImpl implements IStudentService {
    private IStudentDao dao;
    public void setDao(IStudentDao dao) {
        this.dao = dao;
    }
    @Override
    public void addStudent(Student student) {
        dao.insertStudent(student);
    }
}
