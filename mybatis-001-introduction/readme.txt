开发第一个MyBatis程序
1. resources目录：
    放在这个目录当中的都是资源文件、配置文件
    直接放到resources目录下的资源等同于放到类的根路径下

2. 开发步骤
    1. 打包方式jar
    2. 引入依赖
        - MyBatis依赖
        - MySQL驱动
    3. 编写MyBatis核心配置文件mybatis-config.xml
        注意： 1. 这个文件名不是必须叫做mybatis-config.xml
              2. 文件存放的位置也不是固定的，一般情况下会放到类的根路径下
   4. 编写XxxMapper.xml
        这个文件名也不是固定的， 放的位置也不固定，我们起名为CarMapper
        把他暂时放到类的根路径下
   5. 通过<mapper>标签的resource属性关联配置文件即XxxMapper.xml
   6. 编写MyBatis程序
        在MyBatis中负责执行SQL语句的那个对象叫什么？
            SqlSession
        SqlSession是专门用来执行SQL语句的，是一次java程序和数据库之间的一次会话
        要想获取SqlSession对象，得先获取SqlSessionFactory对象
        通过SqlSessionFactoryBuilder的build方法来获取SqlSessionFactory对象

        MyBatis的核心对象包括：
            SqlSessionFactoryBuilder
            SqlSessionFactory
            SqlSession


3. 从XML中构建SqlSessionFactory
    1. MyBatis中有个很重要的对象为SqlSessionFactory
    2. SQLSessionFactory的创建需要XML

4. MyBatis中有两个主要配置文件
    其中一个是mybatis-config.xml这是核心配置文件，主要配置连接数据库的信息等
    另一个是XxxMapper.xml,这个是专门用来编写SQL语句的XML文件

5. 小细节
    mybatis中sql语句的结尾分号可不加
    Resources.getResourceAsStream
        凡是遇到resource这种单词大部分情况下就是从类的根路径下开始加载
    FileInputStream fileInputStream = new FileInputStream("d:\\mybatis.xml");
        采用这种方式也可以，但可移植性太差，移植到其他操作系统会导致路径失效，修改代码违背OCP
    已经验证了：
        mybatis核心配置文件的名字和存放路径任意，但为了程序的健壮性，最好将配置文件放到类路径下面
        InputStream is= Resources.getResourceAsStream("com/mybatis.xml");
        实际上就是InputStream resourceAsStream =
        ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");

6. 关于MyBatis事务管理机制（深度剖析）
    在mybatis-config.xml中可以通过以下配置进行MyBatis的事务管理
        <transactionManager type="JDBC"/>
        type属性的值有两个[JDBC|MANAGED]
        在mybatis中提供两种事务管理机制
        JDBC
            mybatis自己管理事务，自己采用原生的jdbc代码去管理
                conn.setAutoCommit(false);开启事务
                ...业务处理...
                conn.commit()手动提交;
            若执行以下代码
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            则自动提交， 表示没有事务，每一条DML语句都会提交一次
        MANAGED
            mybatis不再进行事务管理了，交给其他容器负责，例如spring
            若当前情况下事务配置为MANAGED则事务是没人管的

    JDBC中的事务，如果未执行 conn.setAutoCommit(false);则默认autoCommit是true

7. MyBatis集成日志组件，让我们调试起来更方便
    mybatis常见的集成日志组件有哪些？
        SLF4J | LOG4J | LOG4J2 | STDOUT_LOGGING
        其中STDOUT_LOGGING是标准日志，MyBatis已经实现了这种标准日志只需开启即可使用<settings>标签进行开启
        mybatis configuration的标签内有顺序。
        log4j、log4j2、logback都是同一个作者

    集成logback框架（使用1.2版本的logback能与配置文件配套）
        logback日志框架是集成slf4j标准。（沙拉疯：日志门面， 日志标准）
        1. 引入logback依赖
        2. 引入logback所需的配置文件
            文件名称必须叫做logback.xml或logback-test.xml, 不能改名
            配置文件必须放到类的根路径下





















