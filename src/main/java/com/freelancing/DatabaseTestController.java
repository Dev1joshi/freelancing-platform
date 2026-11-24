
package com.freelancing;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseTestController {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseTestController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @GetMapping("/db-check")
    public String checkDatabase() {
        try {
            String databaseName = jdbcTemplate.queryForObject(
                    "SELECT DATABASE()",
                    String.class
            );

            return "MySQL Connected Successfully! Database: " + databaseName;

        } catch (Exception e) {
            Throwable cause = e;

            while (cause.getCause() != null) {
                cause = cause.getCause();
            }

            return "Root Cause: " + cause.getClass().getSimpleName()
                    + " - " + cause.getMessage();
        }
    }

}
