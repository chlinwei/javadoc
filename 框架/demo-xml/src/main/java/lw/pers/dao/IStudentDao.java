package lw.pers.dao;

import lw.pers.beans.Student;

//dao不需要实现类,由mybatis生成
//mybatis的映射文件在dao包下,和此接口名称一样
public interface IStudentDao {
    void insertStudent(Student student);
}
