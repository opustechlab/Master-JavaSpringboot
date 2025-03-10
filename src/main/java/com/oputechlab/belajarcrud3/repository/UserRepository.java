package com.oputechlab.belajarcrud3.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getAllUsers() {
        return jdbcTemplate.queryForList("SELECT * FROM User", Collections.emptyMap());
    }

    public int deleteUser(int id) {
        String sql = "DELETE FROM User WHERE id = :id";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        return jdbcTemplate.update(sql, params);
    }
}
