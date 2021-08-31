package com.prepared.SpringBootUnitTest.test.method2;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

/**
 * 建议使用，按需加载对应的Mapper文件
 *
 * @author prepared
 * @date 2021-06-08 4:38 PM
 */
public class Mybatis3Utils {

    public static final SqlSessionFactory sqlSessionFactory;
    public static final ThreadLocal<SqlSession> sessionThread = new ThreadLocal<>();

    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config-test.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSession getCurrentSqlSession() {
        SqlSession sqlSession = sessionThread.get();
        if (Objects.isNull(sqlSession)) {
            sqlSession = sqlSessionFactory.openSession();
            sessionThread.set(sqlSession);
        }
        return sqlSession;
    }

    public static void closeCurrentSession() {
        SqlSession sqlSession = sessionThread.get();
        if (Objects.nonNull(sqlSession)) {
            sqlSession.close();
        }
        sessionThread.set(null);
    }
}