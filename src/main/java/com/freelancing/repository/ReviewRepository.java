
package com.freelancing.repository;

import com.freelancing.model.Review;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Review> findAll() {
        String sql = "SELECT * FROM reviews ORDER BY created_at DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Review review = new Review();

            review.setReviewId(rs.getInt("review_id"));
            review.setContractId(rs.getInt("contract_id"));
            review.setClientId(rs.getInt("client_id"));
            review.setFreelancerId(rs.getInt("freelancer_id"));
            review.setRating(rs.getInt("rating"));
            review.setReviewComment(rs.getString("review_comment"));

            java.sql.Timestamp createdAt = rs.getTimestamp("created_at");

            if (createdAt != null) {
                review.setCreatedAt(createdAt.toLocalDateTime());
            }

            return review;
        });
    }

    public void save(Review review) {
        String sql = """
                INSERT INTO reviews
                (contract_id, client_id, freelancer_id, rating, review_comment)
                VALUES (?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(sql,
                review.getContractId(),
                review.getClientId(),
                review.getFreelancerId(),
                review.getRating(),
                review.getReviewComment());
    }
}
