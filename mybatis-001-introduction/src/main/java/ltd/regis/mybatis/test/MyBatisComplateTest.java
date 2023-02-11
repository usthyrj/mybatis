package ltd.regis.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @BelongsProject: mybatis
 * @BelongsPackage: ltd.regis.mybatis.test
 * @Author: Regis
 * @CreateTime: 2023-02-11  10:30
 * @Description: 采用正规的方式写一个完整版的MyBatis程序
 * @Version: 1.0
 */

public class MyBatisComplateTest {

    private static SqlSession sqlSession;

    public static void main(String[] args) {
        try {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
            sqlSession = sqlSessionFactory.openSession();
            int count = sqlSession.insert("insertCar");
            System.out.println(count);
            sqlSession.commit();

        } catch (IOException e) {
            assert sqlSession != null;
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            assert sqlSession != null;
            sqlSession.close();
        }
    }
}
