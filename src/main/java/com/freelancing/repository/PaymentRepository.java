
package com.freelancing.repository;

import com.freelancing.model.Payment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentRepository {

    private final JdbcTemplate jdbcTemplate;

    public PaymentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Payment> findAll() {
        String sql = "SELECT * FROM payments ORDER BY payment_id DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Payment payment = new Payment();

            payment.setPaymentId(rs.getInt("payment_id"));
            payment.setContractId(rs.getInt("contract_id"));
            payment.setAmount(rs.getBigDecimal("amount"));

            java.sql.Timestamp paymentDate =
                    rs.getTimestamp("payment_date");

            if (paymentDate != null) {
                payment.setPaymentDate(paymentDate.toLocalDateTime());
            }

            payment.setPaymentMethod(rs.getString("payment_method"));
            payment.setStatus(rs.getString("status"));
            payment.setTransactionReference(
                    rs.getString("transaction_reference"));

            return payment;
        });
    }

    public void save(Payment payment) {
        String sql = """
                INSERT INTO payments
                (contract_id, amount, payment_method, status, transaction_reference)
                VALUES (?, ?, ?, 'PENDING', ?)
                """;

        jdbcTemplate.update(sql,
                payment.getContractId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getTransactionReference());
    }

    public void updateStatus(int paymentId, String status) {
        String sql = "UPDATE payments SET status = ? WHERE payment_id = ?";
        jdbcTemplate.update(sql, status, paymentId);
    }


    public String getContractPaymentStatus(int contractId) {

        String sql = """
        SELECT
            CASE
                WHEN COUNT(*) = 0 THEN NULL
                WHEN SUM(status = 'COMPLETED') > 0 THEN 'COMPLETED'
                WHEN SUM(status = 'PENDING') > 0 THEN 'PENDING'
                ELSE 'FAILED'
            END
        FROM payments
        WHERE contract_id = ?
        """;

        return jdbcTemplate.queryForObject(
                sql, String.class, contractId);
    }


    public boolean hasActivePayment(int contractId) {
        String sql = """
                SELECT COUNT(*)
                FROM payments
                WHERE contract_id = ?
                AND status IN ('PENDING', 'COMPLETED')
                """;

        Integer count = jdbcTemplate.queryForObject(
                sql, Integer.class, contractId);

        return count != null && count > 0;
    }
}
