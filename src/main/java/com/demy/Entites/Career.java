package com.demy.Entites;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="careers")
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "status", length = 255)
    private String status;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "details", columnDefinition = "TEXT")
    private String details;

    @Column(name = "resume_path")
    private String resumePath;

    // Constructors, getters, and setters
    // Constructors
    public Career() {
    }

    public Career(String name, String phone, String email, String status, Integer experience, String details, String resumePath) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.experience = experience;
        this.details = details;
        this.resumePath = resumePath;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Career{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", experience=" + experience +
                ", details='" + details + '\'' +
                ", resumePath='" + resumePath + '\'' +
                '}';
    }
}
