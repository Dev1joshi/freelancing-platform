
package com.freelancing.repository;

import com.freelancing.model.Freelancer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FreelancerRepository {

    private final JdbcTemplate jdbcTemplate;

    public FreelancerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Freelancer> findAll() {
        String sql = "SELECT * FROM freelancers ORDER BY freelancer_id DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Freelancer freelancer = new Freelancer();
            freelancer.setFreelancerId(rs.getInt("freelancer_id"));
            freelancer.setFullName(rs.getString("full_name"));
            freelancer.setEmail(rs.getString("email"));
            freelancer.setPassword(rs.getString("password"));
            freelancer.setPhone(rs.getString("phone"));
            freelancer.setExperienceYears(rs.getInt("experience_years"));
            freelancer.setBio(rs.getString("bio"));
            return freelancer;
        });
    }

    public Freelancer findById(int id) {
        String sql = "SELECT * FROM freelancers WHERE freelancer_id = ?";

        List<Freelancer> freelancers = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Freelancer freelancer = new Freelancer();
            freelancer.setFreelancerId(rs.getInt("freelancer_id"));
            freelancer.setFullName(rs.getString("full_name"));
            freelancer.setEmail(rs.getString("email"));
            freelancer.setPassword(rs.getString("password"));
            freelancer.setPhone(rs.getString("phone"));
            freelancer.setExperienceYears(rs.getInt("experience_years"));
            freelancer.setBio(rs.getString("bio"));
            return freelancer;
        }, id);

        return freelancers.isEmpty() ? null : freelancers.get(0);
    }

    public void save(Freelancer freelancer) {
        String sql = """
                INSERT INTO freelancers
                (full_name, email, password, phone, experience_years, bio)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(sql,
                freelancer.getFullName(),
                freelancer.getEmail(),
                freelancer.getPassword(),
                freelancer.getPhone(),
                freelancer.getExperienceYears(),
                freelancer.getBio());
    }
}
