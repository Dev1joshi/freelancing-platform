
package com.freelancing.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Proposal {

    private int proposalId;
    private int projectId;
    private int freelancerId;
    private BigDecimal proposedAmount;
    private String coverLetter;
    private String status;
    private LocalDateTime submittedAt;

    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(int freelancerId) {
        this.freelancerId = freelancerId;
    }

    public BigDecimal getProposedAmount() {
        return proposedAmount;
    }

    public void setProposedAmount(BigDecimal proposedAmount) {
        this.proposedAmount = proposedAmount;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
