package com.app.planm;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlanmApplicationTests {
	 
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Test
	void contextLoads() {
	}

	@Test
    public void connectionTest() {
        try (Connection con = sqlSessionFactory.openSession().getConnection()) {
            System.out.println("Success");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	
}
