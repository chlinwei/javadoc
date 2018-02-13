package dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import annotation.Column;
import annotation.Table;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.JdbcUtil;

/**
 * 基础dao
 * @author APPle
 *
 */
public class BaseDao<T> {
    //具体的dao上面的泛型类型
    private Class targetClass;
    //表名
    private String tableName;

    public BaseDao(){
        /**
         * 需要解决的问题：
         * 1） 得到具体的业务dao运行过程中的泛型具体类型（Student/Teacher）,可以封装ResultSet
         * 2) 得到泛型具有类型名称 ，就是表名
         */
        //1)this : 代表当前运行的dao对象
        //System.out.println(this.getClass());
        //2)this.getClass(): 代表当前运行dao对象的Class对象
        Class clazz = this.getClass();   //public class TeacherDao extends dao.BaseDao<Teacher>
        //3)clazz.getGenericSuperclass()： 得到当前dao对象的父类（参数化类型）
        Type type = clazz.getGenericSuperclass(); // dao.BaseDao<Teacher>
        //4)把父类的类型强转成子类（参数化类型: ParameterizedType）
        ParameterizedType param = ( ParameterizedType)type; // dao.BaseDao<Teacher>
        //5)param.getActualTypeArguments():得到参数化类型 上面的泛型类型列表
        Type[] types = param.getActualTypeArguments(); // <Teacher>
        //6)取出泛型类型列表中的第一个泛型类型
        Type target = types[0];  //  Teacher
        //7)强制成Class类型
        targetClass = (Class)target;

        try {
            //System.out.println(targetClass.getSimpleName());
            /**
             * 获取表名 来自于 类上面的注解
             */
            Table table = (Table)targetClass.getAnnotation(Table.class);
            tableName = table.name();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public List<T> findAll(){
        try {
            QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
            return (List<T>)qr.query("select * from "+tableName+"", new MyBeanListHandler());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public T findById(int id){
        try {
            QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
            return (T)qr.query("select * from "+tableName+" where id=?", new BeanHandler(targetClass),new Object[]{id});
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 自行设计一个封装多个对象的List集合的ResultSetHandler
     */
    class MyBeanListHandler implements ResultSetHandler{

        @Override
        public Object handle(ResultSet rs) throws SQLException {
            try {
                List<T> list = new ArrayList<T>();
                //得到结果集的元数据
                ResultSetMetaData rsmd = rs.getMetaData();
                //得到表的列数量
                int columnCount = rsmd.getColumnCount();
                while(rs.next()){//行
                    //创建对象
                    T obj = (T)targetClass.newInstance();
                    //把字段值封装到对象中
                    //遍历列
                    for(int i=1;i<=columnCount;i++){
                        //得到列的值
                        Object value = rs.getObject(i);
                        //得到列名称
                        String columnName = rsmd.getColumnName(i).toLowerCase();

                        //遍历所有属性
                        Field[] fields = targetClass.getDeclaredFields();
                        for (Field field : fields) {
                            //得到属性上面的注解
                            Column column = field.getAnnotation(Column.class);
                            //得到注解的内容
                            String cname = column.name().toLowerCase();

                            if(columnName.equals(cname)){
                                field.setAccessible(true);
                                //我需要赋值的属性,给属性赋值
                                field.set(obj, value);
                                break;
                            }
                        }
                    }
                    //把封装好的对象放入LIst集合中
                    list.add(obj);
                }
                return list;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }
}








