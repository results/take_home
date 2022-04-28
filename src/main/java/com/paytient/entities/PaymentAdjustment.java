package com.paytient.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * Payment adjustments (basically promo codes or similar) to be used for match discount
 * @author John Fink
 *
 */
@Entity
@Table(name="payment_adjustments")
public class PaymentAdjustment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="adjustment_percent")
    private BigDecimal adjustmentPercent;
    
    @Column(name="adjustment_threshold")
    private BigDecimal adjustmentThreshold;
    
    @Column(name="adjustment_code")
    private String adjustmentCode;

	@Column(name="description")
    private String description;
	    
    @Column(name="expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;    
    
    @Column(name="updated_at", nullable=false)
    @UpdateTimestamp
    private Timestamp updatedAt;
    
    @Column(name="created_at", nullable=false)
    @CreationTimestamp
    private Timestamp createdAt;
    
    @Column(name="active", nullable=false)
    private boolean active = true;
    
    
    public PaymentAdjustment() {
	}


	public PaymentAdjustment(BigDecimal adjustmentPercent, BigDecimal adjustmentThreshold, String adjustmentCode,
			String description, Date expirationDate) {
		this.adjustmentPercent = adjustmentPercent;
		this.adjustmentThreshold = adjustmentThreshold;
		this.adjustmentCode = adjustmentCode;
		this.description = description;
		this.expirationDate = expirationDate;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAdjustmentPercent() {
		return adjustmentPercent;
	}
	
    public String getAdjustmentCode() {
		return adjustmentCode;
	}

	public void setAdjustmentCode(String adjustmentCode) {
		this.adjustmentCode = adjustmentCode;
	}

	public void setAdjustmentPercent(BigDecimal adjustmentPercent) {
		this.adjustmentPercent = adjustmentPercent;
	}

	public BigDecimal getAdjustmentThreshold() {
		return adjustmentThreshold;
	}

	public void setAdjustmentThreshold(BigDecimal adjustmentThreshold) {
		this.adjustmentThreshold = adjustmentThreshold;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
    
    
	
}
