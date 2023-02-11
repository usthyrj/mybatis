package ltd.regis.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @BelongsProject: mybatis
 * @BelongsPackage: ltd.regis.mybatis.test
 * @Author: Regis
 * @CreateTime: 2023-02-11  10:42
 * @Description:
 * @Version: 1.0
 */


//测试类名为要测试的类 + test
public class MyBatisComplateTestTest {


    //方法名为test + 要测试的方法名
    @Test
    public void testCar() {
        SqlSession sqlSession = null;
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

    @Test
    public void testMathService() {
        int a = 3, b = 2;

        int actval = 6;
        int expval = 5;
        //加断言进行测试
        Assertions.assertEquals(expval, actval);

    }

}
