package dao;
import java.util.List;
import entity.Student;
import entity.Teacher;
import org.junit.Test;

public class TestDao {
    /*
    CREATE TABLE teacher_list(
  id int PRIMARY KEY AUTO_INCREMENT,
  tname VARCHAR(20),
  tage int
);
INSERT INTO teacher_list(tname,tage) VALUES ("李老师",34);

CREATE TABLE student_list(
  id int PRIMARY KEY AUTO_INCREMENT,
  tname VARCHAR(20),
  tage int
);

INSERT INTO student_list(tname,tage) VALUES ("王同学",14);
     */
    @Test
    public void testStu(){
        StudentDao stuDao = new StudentDao();
        List<Student> list = stuDao.findAll();

        for (Student student : list) {
            System.out.println(student);
        }
    }
    @Test
    public void testTea(){
        TeacherDao teaDao = new TeacherDao();
        List<Teacher> list = teaDao.findAll();
        for (Teacher teacher : list) {
            System.out.println(teacher);
        }

    }
}

