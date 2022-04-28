package com.paytient.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="users")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    	
    @Column(name="first_name", nullable=false)
	private String firstName;
	
    @Column(name="last_name", nullable=false)
	private String lastName;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_token", referencedColumnName = "id")
    private AuthToken session;
	
    @OneToOne( optional = false, orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinColumn(name = "balance_id" ,referencedColumnName="id")
    private Balance balance = new Balance(this);
	
    @OneToMany(mappedBy ="user", cascade = CascadeType.ALL)
    private List<PaymentMethod> paymentMethods = new ArrayList<>();
    
    @OneToMany(mappedBy ="user", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();
    
    @Column(name="updated_at", nullable=false)
    @UpdateTimestamp
    private Timestamp updatedAt;
    
    @Column(name="created_at", nullable=false)
    @CreationTimestamp
    private Timestamp createdAt;
    
    @Column(name="active", nullable=false)
    private boolean active = true;
    
    public User() {
		// TODO Auto-generated constructor stub
	}
    
	public User(String firstName, String lastName, Balance balance) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public AuthToken getSession() {
		return session;
	}

	public void setSession(AuthToken session) {
		this.session = session;
	}

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}

	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
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
