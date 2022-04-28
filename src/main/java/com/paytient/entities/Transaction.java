package com.paytient.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

/**
 * Represents a transaction
 * @author John Fink
 *
 */
@Entity
@Table(name="transactions")
public class Transaction {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name="currency_code", nullable=false)
    private String currencyCode = Currency.getInstance(Locale.US).getCurrencyCode();//set default to usd
    
    @Column(name="starting_balance", nullable=false)
    private BigDecimal startingBalance;
    
    @Column(name="transaction_amount", nullable=false)
    private BigDecimal transactionAmount;
    
    @OneToOne
    @JoinColumn(name = "payment_adjustment_id", referencedColumnName = "id")
    private PaymentAdjustment paymentAdjustment;
    
    @Column(name="adjustment_amount")
    private BigDecimal adjustmentAmount;
    
    @Column(name="ending_balance", nullable=false)
    private BigDecimal endingBalance;
    
    @OneToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id")
    private PaymentMethod paymentMethod;
    
    @Column(name="transaction_uuid", nullable=false)
    private UUID transactionUUID = UUID.randomUUID();
        
	@Column(name="created_at", nullable=false)
    @CreationTimestamp
    private Timestamp createdAt;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
    
	public Transaction(User user, BigDecimal startingBalance, BigDecimal transactionAmount,
			PaymentAdjustment paymentAdjustment, BigDecimal adjustmentAmount, BigDecimal endingBalance,
			PaymentMethod paymentMethod) {
		this.user = user;
		this.startingBalance = startingBalance;
		this.transactionAmount = transactionAmount;
		this.paymentAdjustment = paymentAdjustment;
		this.adjustmentAmount = adjustmentAmount;
		this.endingBalance = endingBalance;
		this.paymentMethod = paymentMethod;
	}
	
    public UUID getTransactionUUID() {
		return transactionUUID;
	}

	public void setTransactionUUID(UUID uuid) {
		this.transactionUUID = uuid;
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

	public BigDecimal getStartingBalance() {
		return startingBalance;
	}

	public void setStartingBalance(BigDecimal startingBalance) {
		this.startingBalance = startingBalance;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public PaymentAdjustment getPaymentAdjustment() {
		return paymentAdjustment;
	}

	public void setPaymentAdjustment(PaymentAdjustment paymentAdjustment) {
		this.paymentAdjustment = paymentAdjustment;
	}

	public BigDecimal getAdjustmentAmount() {
		return adjustmentAmount;
	}

	public void setAdjustmentAmount(BigDecimal adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
	}

	public BigDecimal getEndingBalance() {
		return endingBalance;
	}

	public void setEndingBalance(BigDecimal endingBalance) {
		this.endingBalance = endingBalance;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
    
    
    
    

}
