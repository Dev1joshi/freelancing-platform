
package com.freelancing.repository;

import com.freelancing.model.Client;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Client> findAll() {
        String sql = "SELECT * FROM clients ORDER BY client_id DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> mapClient(rs));
    }

    public Client findById(int id) {
        String sql = "SELECT * FROM clients WHERE client_id = ?";

        List<Client> clients = jdbcTemplate.query(
                sql, (rs, rowNum) -> mapClient(rs), id);

        return clients.isEmpty() ? null : clients.get(0);
    }

    public Client findByEmail(String email) {
        String sql = "SELECT * FROM clients WHERE email = ?";

        List<Client> clients = jdbcTemplate.query(
                sql, (rs, rowNum) -> mapClient(rs), email);

        return clients.isEmpty() ? null : clients.get(0);
    }

    public void save(Client client) {
        String sql = """
                INSERT INTO clients (full_name, email, password, phone)
                VALUES (?, ?, ?, ?)
                """;

        jdbcTemplate.update(sql,
                client.getFullName(),
                client.getEmail(),
                client.getPassword(),
                client.getPhone());
    }

    private Client mapClient(java.sql.ResultSet rs)
            throws java.sql.SQLException {

        Client client = new Client();

        client.setClientId(rs.getInt("client_id"));
        client.setFullName(rs.getString("full_name"));
        client.setEmail(rs.getString("email"));
        client.setPassword(rs.getString("password"));
        client.setPhone(rs.getString("phone"));

        return client;
    }
}
