package com.paytient.entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.paytient.constants.PaymentType;

/**
 * Mock payment method. Enough to represent a payment method for demonstrative purposes.
 * @author John Fink
 *
 */
@Entity
@Table(name="payment_methods")
public class PaymentMethod {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name="payment_type")
    private PaymentType paymentType;
    
    @Column(name="account_number", nullable=false)
    private String accountNumber = "";
    
    @Column(name="payment_uuid", nullable=false)
    private UUID paymentUUID = UUID.randomUUID();
    	
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
    
    public PaymentMethod() {
		// TODO Auto-generated constructor stub
	} 
    
   	public PaymentMethod(User user, PaymentType paymentType, String accountNumber, Date expirationDate) {
		this.user = user;
		this.paymentType = paymentType;
		this.accountNumber = accountNumber;
		this.expirationDate = expirationDate;
	}
   	
    public UUID getPaymentUUID() {
		return paymentUUID;
	}

	public void setPaymentUUID(UUID paymentUUID) {
		this.paymentUUID = paymentUUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
