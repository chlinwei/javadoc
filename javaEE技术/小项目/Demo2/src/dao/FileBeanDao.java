package dao;

import entity.FileBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.JdbcUtil;

import java.sql.SQLException;
import java.util.List;

public class FileBeanDao {
    /**
     * 保存文件信息的方法
     */
    public void saveFile(FileBean bean){
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        try {
            qr.update("insert into file_list(name,size,type,addTime,file_path,info) values(?,?,?,?,?,?)",
                            bean.getName(),
                            bean.getSize(),
                            bean.getType(),
                            bean.getAddTime(),
                            bean.getFile_path(),
                            bean.getInfo()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据id查询文件
     */
    public FileBean findById(int id){
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        FileBean bean = null;
        try {
            bean = qr.query("select * from file_list where id=?",new BeanHandler<FileBean>(FileBean.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return bean;
    }


    /**
     *查询所有文件
     */

    public List<FileBean> findAll(){
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        try {
            return qr.query("select * from file_list",new BeanListHandler<FileBean>(FileBean.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static void main(String args[]){
        FileBeanDao dao = new FileBeanDao();
        FileBean bean = new FileBean();
        bean.setInfo("描述");
        bean.setName("a.jpg");
        bean.setSize("23kb");
        bean.setType("image");
        bean.setAddTime("2017-1-11 15:09:32");
        bean.setFile_path("/upload/10/3/a.jpg");
        dao.saveFile(bean);//测试
        //System.out.pritnln(dao.findById(1));
    }
}



