package com.example.cojorca.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="userInfo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="login")
    private String loginId;

    @Column(name="name")
    private String name;

    @Column(name="html_url")
    private String html_url;

    @Column(name="email")
    private String email;

    @Column(name="createdAt")
    private String createdAt;

    @ManyToMany(mappedBy = "visitors")
    private List<Cafe> visitedCafes;

    public User(String login, String name, String html_url, String createdAt) {
        this.loginId = login;
        this.name = name;
        this.html_url = html_url;
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

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
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
