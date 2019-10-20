package com;

import com.shop.entity.LoggingInfoPO;
import com.shop.entity.TestEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author ChenTengfei
 * @date 2019/8/12 18:57
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-hive.xml"})
public class HiveTest {
    /*private ApplicationContext context = null;
    private JdbcTemplate jdbcTemplate;
    {
        context = new ClassPathXmlApplicationContext("spring-hive.xml");
        jdbcTemplate = (JdbcTemplate) context.getBean("hiveTemplate");
    }*/

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Test
    public void testDate() throws ParseException {
        String str = "2019-10-10 19:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.parse(str));
    }

    @Test
    public void queryLogging(){
        //开始时间和结束时间格式为yymmdd
        String beginDay = "20190303";
        String endDay = "20191103";

        beginDay = change(beginDay);
        endDay = change(endDay);
        String sql = "select * from logging where time>="+beginDay+"and time <="+endDay;
        RowMapper<LoggingInfoPO> loggingInfoPORowMapper = new BeanPropertyRowMapper<>(LoggingInfoPO.class);
        List<LoggingInfoPO> list = jdbcTemplate.query(sql,loggingInfoPORowMapper);
        System.out.println(list);

    }
    public String change(String time){
        StringBuffer day =new StringBuffer(time);
        day.insert(4,'-');
        day.insert(7,'-');
        return new String(day);
    }

    @Test
    public void queryObject(){
        String sql = "select id,name from student where id = ? ";
        RowMapper<TestEntity> entityRowMapper = new BeanPropertyRowMapper<>(TestEntity.class);
        TestEntity entity = jdbcTemplate.queryForObject(sql,entityRowMapper);
        System.out.println(entity);
    }

    @Test
    public void queryObjects(){
        String sql = "select id,name from student";
        RowMapper<TestEntity> entityRowMapper = new BeanPropertyRowMapper<>(TestEntity.class);
        List<TestEntity> entities = jdbcTemplate.query(sql,entityRowMapper);
        System.out.println(entities);
    }

    @Test
    public void update(){
        String sql = "insert into student values(5,'tengfei')";
        jdbcTemplate.update(sql);
    }
}