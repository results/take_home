package com.paytient.entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

/**
 * Auth token. Placed in header of request. Expires after x time
 * @author John Fink
 *
 */
@Entity
@Table(name="auth_tokens")
public class AuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(mappedBy = "session")
    private User user;
    
    @Column(name="token", nullable=false)
    private UUID token = UUID.randomUUID();
    
    @Column(name="expires_at", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiresAt;
        
    @Column(name="created_at", nullable=false)
    @CreationTimestamp
    private Timestamp createdAt;
    
    @Column(name="active", nullable=false)
    private boolean active = true;
        
    public AuthToken() {
  
    }
    
	public AuthToken(User user, UUID token, Date expiresAt) {
		this.user = user;
		this.token = token;
		this.expiresAt = expiresAt;
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

	public UUID getToken() {
		return token;
	}

	public void setToken(UUID token) {
		this.token = token;
	}

	public Date getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
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
