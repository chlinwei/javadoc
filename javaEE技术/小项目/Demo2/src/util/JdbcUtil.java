package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

/**
 * 连接池版本的JdbcUtil
 */
public class JdbcUtil {
    //创建连接池对象
    private static DataSource ds = new ComboPooledDataSource();
    public static DataSource getDataSource(){
        return ds;
    }
}



