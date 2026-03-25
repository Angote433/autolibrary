package com.arnold.autolibrary.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="userdetails")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(nullable = false , length = 100)
    private String fullName;

    @Column(nullable = false,unique = true , length = 50)
    private String userName;

    @Column(nullable = false,length = 255)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name="streamID")
    private Stream stream;

    @Column(nullable = false)
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name="createdBy")
    private UserDetails createdBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    public UserDetails(){}
    public UserDetails(String fullName,String userName,String passwordHash,Role role,boolean isActive){
        this.fullName = fullName;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.role = role;
        this.isActive = isActive;
    }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Stream getStream() { return stream; }
    public void setStream(Stream stream) { this.stream = stream; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean isActive) { this.isActive = isActive; }

    public UserDetails getCreatedBy() { return createdBy; }
    public void setCreatedBy(UserDetails createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }


}
