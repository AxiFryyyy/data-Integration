package com.departmentb_system.dao.impl;

import com.departmentb_system.PO.User;
import com.departmentb_system.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserByUN(String userName) {
        String sql = "SELECT * FROM Account WHERE username = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        try{
            User user = jdbcTemplate.queryForObject(sql, rowMapper, userName);
            return user;
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }
}
