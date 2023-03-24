package com.example.cojorca.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "name")
    private String name;

    @Column(name = "html_url")
    private String htmlUrl;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private String createdAt;

    @ManyToMany(mappedBy = "visitors")
    private List<Cafe> visitedCafes;

    public User(String loginId, String name, String htmlUrl, String createdAt) {
        this.loginId = loginId;
        this.name = name;
        this.htmlUrl = htmlUrl;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Cafe> getVisitedCafes() {
        return visitedCafes;
    }

    public void setVisitedCafes(List<Cafe> visitedCafes) {
        this.visitedCafes = visitedCafes;
    }
}
