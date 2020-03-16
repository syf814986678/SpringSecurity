package com.shiyifan.Controller;

import com.shiyifan.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@RestController
public class JdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;
    @Autowired
    UserInfo userInfo;
    //查询数据库的所有信息
    @RequestMapping("/userlist")
    public List<Map<String,Object>> userlist(){
        String sql="select password from login where username = 'ceshi'";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }
    @RequestMapping("/adduser")
    public String adduser(){
        String sql="insert into login values('ceshi','ceshi','123.0','0')";
        jdbcTemplate.update(sql);
        return "update-ok";
    }
    @RequestMapping("/password")
    public UserInfo password() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from security where username = 'syf'");
        while (rs.next()){
            userInfo.setUsername(rs.getString(1));
            userInfo.setPassword(rs.getString(2));
            userInfo.setRole(rs.getString(3));
            System.out.println(rs.getString(3));
        }
        return userInfo;

    }
}
