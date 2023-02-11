package ltd.regis.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @BelongsProject: mybatis
 * @BelongsPackage: ltd.regis.mybatis.test
 * @Author: Regis
 * @CreateTime: 2023-02-11  08:26
 * @Description:
 * @Version: 1.0
 */

public class MyBatisIntroduction {
    public static void main(String[] args) throws IOException {
        //获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //获取SqlSessionFactory
        //build(InputStream inputStream)inputstream指向核心配置文件
//        InputStream is= Resources.getResourceAsStream("mybatis-config.xml");
        InputStream is= Resources.getResourceAsStream("mybatis-config.xml");
//        FileInputStream fileInputStream = new FileInputStream("d:\\mybatis.xml");
//        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
        //一般一个数据库一个SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();//事务管理器是JDBC，底层会执行conn.setAutoCommit(false);


        //执行sql语句insert(sql语句的id)
        int count = sqlSession.insert("insertCar");

        //手动提交
        sqlSession.commit();

        System.out.println("插入了"+count+"条记录");

        //关闭SqlSession
        sqlSession.close();

    }
}
