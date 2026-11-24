
package com.freelancing.repository;

import com.freelancing.model.Project;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Project> findAll() {
        String sql = "SELECT * FROM projects ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapProject(rs));
    }

    public Project findById(int projectId) {
        String sql = "SELECT * FROM projects WHERE project_id = ?";

        List<Project> projects = jdbcTemplate.query(
                sql, (rs, rowNum) -> mapProject(rs), projectId);

        return projects.isEmpty() ? null : projects.get(0);
    }

    public List<Project> findByClientId(int clientId) {
        String sql = "SELECT * FROM projects WHERE client_id = ?";
        return jdbcTemplate.query(
                sql, (rs, rowNum) -> mapProject(rs), clientId);
    }

    public void save(Project project) {
        String sql = """
                INSERT INTO projects
                (client_id, title, description, budget, deadline, status)
                VALUES (?, ?, ?, ?, ?, 'OPEN')
                """;

        jdbcTemplate.update(sql,
                project.getClientId(),
                project.getTitle(),
                project.getDescription(),
                project.getBudget(),
                project.getDeadline());
    }

    public void updateStatus(int projectId, String status) {
        String sql = "UPDATE projects SET status = ? WHERE project_id = ?";
        jdbcTemplate.update(sql, status, projectId);
    }

    private Project mapProject(java.sql.ResultSet rs)
            throws java.sql.SQLException {

        Project project = new Project();

        project.setProjectId(rs.getInt("project_id"));
        project.setClientId(rs.getInt("client_id"));
        project.setTitle(rs.getString("title"));
        project.setDescription(rs.getString("description"));
        project.setBudget(rs.getBigDecimal("budget"));

        java.sql.Date deadline = rs.getDate("deadline");

        if (deadline != null) {
            project.setDeadline(deadline.toLocalDate());
        }

        project.setStatus(rs.getString("status"));

        return project;
    }
}
