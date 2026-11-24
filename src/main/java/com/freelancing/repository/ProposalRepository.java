
package com.freelancing.repository;

import com.freelancing.model.Proposal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProposalRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProposalRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Proposal> findAll() {
        String sql = "SELECT * FROM proposals ORDER BY submitted_at DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapProposal(rs));
    }

    public Proposal findById(int proposalId) {
        String sql = "SELECT * FROM proposals WHERE proposal_id = ?";

        List<Proposal> proposals = jdbcTemplate.query(
                sql, (rs, rowNum) -> mapProposal(rs), proposalId);

        return proposals.isEmpty() ? null : proposals.get(0);
    }

    public List<Proposal> findByProjectId(int projectId) {
        String sql = "SELECT * FROM proposals WHERE project_id = ?";
        return jdbcTemplate.query(
                sql, (rs, rowNum) -> mapProposal(rs), projectId);
    }

    public void save(Proposal proposal) {
        String sql = """
                INSERT INTO proposals
                (project_id, freelancer_id, proposed_amount, cover_letter, status)
                VALUES (?, ?, ?, ?, 'PENDING')
                """;

        jdbcTemplate.update(sql,
                proposal.getProjectId(),
                proposal.getFreelancerId(),
                proposal.getProposedAmount(),
                proposal.getCoverLetter());
    }

    public void updateStatus(int proposalId, String status) {
        String sql = "UPDATE proposals SET status = ? WHERE proposal_id = ?";
        jdbcTemplate.update(sql, status, proposalId);
    }

    public void rejectOtherProposals(int projectId, int acceptedProposalId) {
        String sql = """
                UPDATE proposals
                SET status = 'REJECTED'
                WHERE project_id = ?
                AND proposal_id <> ?
                AND status = 'PENDING'
                """;

        jdbcTemplate.update(sql, projectId, acceptedProposalId);
    }

    private Proposal mapProposal(java.sql.ResultSet rs)
            throws java.sql.SQLException {

        Proposal proposal = new Proposal();

        proposal.setProposalId(rs.getInt("proposal_id"));
        proposal.setProjectId(rs.getInt("project_id"));
        proposal.setFreelancerId(rs.getInt("freelancer_id"));
        proposal.setProposedAmount(rs.getBigDecimal("proposed_amount"));
        proposal.setCoverLetter(rs.getString("cover_letter"));
        proposal.setStatus(rs.getString("status"));

        return proposal;
    }
}
