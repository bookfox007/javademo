
package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
    }

    public User findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new UserRowMapper(), id);
    }

    public int save(User user) {
        return jdbcTemplate.update("INSERT INTO users (name, email) VALUES (?, ?)",
                user.getName(), user.getEmail());
    }

    public int update(User user) {
        return jdbcTemplate.update("UPDATE users SET name = ?, email = ? WHERE id = ?",
                user.getName(), user.getEmail(), user.getId());
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

    private static final class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }
}
