//使用jdbc 实现role的仓储层
package com.example.demo.repository;

import com.example.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    //添加角色
    public int addRole(Role role) {
        String sql = "insert into role (name, description) values (?, ?)";
        return jdbcTemplate.update(sql, role.getName(), role.getDescription());
    }
    
    //更新角色
    public int updateRole(Role role) {
        String sql = "update role set name = ?, description = ? where id = ?";
        return jdbcTemplate.update(sql, role.getName(), role.getDescription(), role.getId());
    }
    
    //删除角色
    public int deleteRole(int id) {
        String sql = "delete from role where id = ?";
        return jdbcTemplate.update(sql, id);
    }
    
    //根据id查询角色
    public Optional<Role> getRoleById(int id) {
        String sql = "select * from role where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                Optional.of(new Role(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ))
        );
    }
    
    //查询所有角色
    public List<Role> getAllRoles() {
        String sql = "select * from role";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Role(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                )
        );
    }
}