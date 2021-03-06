package com.jnshu.task5.test;

import com.jnshu.task5.beans.Login;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class db {
    @Test
    public void test() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        System.out.println(sqlSessionFactory);

        SqlSession session  = sqlSessionFactory.openSession();
        try {
            Login login =
                    session.selectOne("selectLoginById", 1);
            System.out.println(login);
        } finally {
            session.close();
        }
    }

}
