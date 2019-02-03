package com.hiking.repository;

import com.hiking.model.User;
import com.hiking.repository.util.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User createUser(User user) {

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

        insert.setGeneratedKeyName("id");

        Map<String, Object> data = new HashMap();

        data.put("username", user.getUsername());
        data.put("password", user.getPassword());


        List<String> columns = new ArrayList();
        columns.add("username");
        columns.add("password");


        insert.setTableName("users");
        insert.setColumnNames(columns);
        Number id = insert.executeAndReturnKey(data);

        return getUser(id.intValue());
    }

    @Override
    public User getUser(int id) {
        User user = jdbcTemplate.queryForObject("select * from users where id = ?", new UserRowMapper(), id);

        return user;
    }

    @Override
    public List<User> getUsers() {

        List<User> users = jdbcTemplate.query("select * from users", new UserRowMapper());

        return users;
    }

    @Override
    public int getUserIdByName(String username) {


        String sql = "SELECT id FROM users WHERE username = ?";

        int id = (int)jdbcTemplate.queryForObject(
                sql, new Object[] { username }, Integer.class);

        return id;
    }

    @Override
    public int getNumberOfDoneHikes(int user_id) {

        String sql = "SELECT count(*) FROM hike WHERE user_id = ? and done = 1";

        int count = (int) jdbcTemplate.queryForObject(
                sql, new Object[] { user_id }, Integer.class);


        return count;

    }

    @Override
    public int getNumberOfHikesTodo(int user_id){

        String sql = "SELECT count(*) FROM hike where user_id = ? and done = 0";

        int count = (int) jdbcTemplate.queryForObject(
                sql, new Object[] { user_id}, Integer.class);

        return count;

    }

    @Override
    public double getNbrOfKmsWalked(int user_id) {

        String sql = "SELECT count(*) FROM hike WHERE user_id = ? and done = 1";

        int count = (int) jdbcTemplate.queryForObject(
                sql, new Object[] { user_id }, Integer.class);


       if(count > 0){
           String sql2 = "SELECT sum(distance) FROM hike where user_id = ? and done = 1";

           double sum = (double)jdbcTemplate.queryForObject(
                   sql2, new Object[] { user_id }, Integer.class);

           return sum;
       }else{

        return 0.00;
    }



}
}
