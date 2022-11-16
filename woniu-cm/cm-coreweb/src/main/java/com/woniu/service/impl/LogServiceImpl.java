package com.woniu.service.impl;

import com.woniu.SysLog;
import com.woniu.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean saveLog(SysLog log) {
        String sql = "INSERT INTO `logs` (`service_id`, `service_type`, `request_param`, `request_at`, `response_result`, `created_at`) VALUES (?, ?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, log.getServiceId());
                preparedStatement.setString(2, log.getServiceType());
                preparedStatement.setString(3, log.getRequestParam());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(log.getRequestDateTime()));
                preparedStatement.setString(5, log.getResponseResult());
                preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            }
        }) > 0;
    }
}
