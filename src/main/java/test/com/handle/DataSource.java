/*
 * SQL2.java
 *
 * Created Date: 2016年8月5日
 *				
 * Copyright (c)  Noah Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Noah Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Noah Technologies Co., Ltd.
 */

package test.com.handle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


/**
 * @author guoshuai
 * @version  <br>
 * <p>类的描述</p>
 */

public class DataSource {
	
    private static HikariDataSource ds;
    private static final QueryRunner runner = new QueryRunner();

   static {
        //连接池配置
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // config.setDriverClassName("com.mysql.jdbc.Driver");
          config.setJdbcUrl("jdbc:sqlserver://192.168.3.199:1433;DatabaseName=VRVEIS");
          //config.setJdbcUrl("jdbc:mysql://192.168.3.199:3306/acess?user=root&password=ibm5c02e&useUnicode=true&characterEncoding=utf8");

        config.setUsername("sa");
        config.setPassword("noah,123");
        config.addDataSourceProperty("cachePrepStmts", true);
        config.addDataSourceProperty("prepStmtCacheSize", 500);
        config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        config.setConnectionTestQuery("SELECT 1");
        config.setAutoCommit(true);
        //池中最小空闲链接数量
        config.setMinimumIdle(10);
        //池中最大链接数量
        config.setMaximumPoolSize(50);
         
        ds = new HikariDataSource(config);
         
    }
    /**
     * 销毁连接池
     */
    public static void shutdown(){
        ds.close();
    }
    
    
    public static <T> List<T> queryBeanList(Class<T> cls, Map<String, String > map,  String sql, Object... params) throws SQLException {
        Connection conn = ds.getConnection();

        List<T> result = null;
        try {
            if ( MapUtils.isNotEmpty(map)) {
                result = runner.query(conn, sql, new BeanListHandler<T>(cls, new BasicRowProcessor(new BeanProcessor(map))), params);
            } else {
                result = runner.query(conn, sql, new BeanListHandler<T>(cls), params);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        shutdown();
        return result;
    }
    
   public static Connection getConnection(){
       try {
           return ds.getConnection();
       } catch (SQLException e) {
           e.printStackTrace();
           ds.resumePool();
           return null;
       }
   }
   
   public static void main(String[] args) throws SQLException {
       System.out.println(getConnection());
       
       //最后关闭链接
   }

}
