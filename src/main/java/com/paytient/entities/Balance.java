package com.paytient.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Represents a users balance.
 * @author John Fink
 *
 */
@Entity
@Table(name="balances")
public class Balance {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(mappedBy = "balance")
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    
    @Column(name="currency_code", nullable=false)
    private String currencyCode = Currency.getInstance(Locale.US).getCurrencyCode();//set default to usd
    
    @Column(name="current_balance", nullable=false)
    private BigDecimal currentBalance = new BigDecimal(0.00);
    
    @Column(name="due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;    
	
    @Column(name="previous_balance")
    private BigDecimal previousBalance = new BigDecimal(0.00);
    
    @Column(name="last_payment_date")
    @Temporal(TemporalType.DATE)
    private Date lastPaymentDate;    
    
    @Column(name="updated_at", nullable=false)
    @UpdateTimestamp
    private Timestamp updatedAt;
    
    @Column(name="created_at", nullable=false)
    @CreationTimestamp
    private Timestamp createdAt;
    
    @Column(name="active", nullable=false)
    private boolean active = true;
    
    
    public Balance() {
    	
    }
    
    public Balance(User user) {
		this.user = user;	
    }
    
	public Balance(User user, BigDecimal currentBalance, Date dueDate) {
		this.user = user;
		this.currentBalance = currentBalance;
		this.dueDate = dueDate;
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

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getPreviousBalance() {
		return previousBalance;
	}

	public void setPreviousBalance(BigDecimal previousBalance) {
		this.previousBalance = previousBalance;
	}

	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}

	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
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
