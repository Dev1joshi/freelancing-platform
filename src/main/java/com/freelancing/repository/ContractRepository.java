
package com.freelancing.repository;

import com.freelancing.model.Contract;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractRepository {

    private final JdbcTemplate jdbcTemplate;

    public ContractRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Contract> findAll() {
        String sql = "SELECT * FROM contracts ORDER BY contract_id DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Contract contract = new Contract();

            contract.setContractId(rs.getInt("contract_id"));
            contract.setProposalId(rs.getInt("proposal_id"));

            java.sql.Date startDate = rs.getDate("start_date");
            java.sql.Date endDate = rs.getDate("end_date");

            if (startDate != null) {
                contract.setStartDate(startDate.toLocalDate());
            }

            if (endDate != null) {
                contract.setEndDate(endDate.toLocalDate());
            }

            contract.setAgreedAmount(rs.getBigDecimal("agreed_amount"));
            contract.setStatus(rs.getString("status"));

            return contract;
        });
    }

    public void save(Contract contract) {
        String sql = """
                INSERT INTO contracts
                (proposal_id, start_date, end_date, agreed_amount, status)
                VALUES (?, ?, ?, ?, 'ACTIVE')
                """;

        jdbcTemplate.update(sql,
                contract.getProposalId(),
                contract.getStartDate(),
                contract.getEndDate(),
                contract.getAgreedAmount());
    }

    public void updateStatus(int contractId, String status) {
        String sql = "UPDATE contracts SET status = ? WHERE contract_id = ?";
        jdbcTemplate.update(sql, status, contractId);
    }
}
