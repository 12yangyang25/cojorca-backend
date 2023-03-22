package com.example.cojorca.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="cafeInfo")
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user; //카페를 등록한 유저 정보
    @Column(name="cafeName")
    private String name; //카페 이름
    @Column(name="address")
    private String address; //카페 주소
    @Column(name="additionalInfo")
    private String additionalInfo; //카페 부가 정보
    @Column(name="imgUrls")
    private String[] imgUrls;
    @ManyToMany
    @JoinTable(
            name = "cafe_visitor",
            joinColumns = @JoinColumn(name = "cafe_id"),
            inverseJoinColumns = @JoinColumn(name = "visitor_id")
    )
    private List<User> visitors;
    @Column(name="createdAt")
    private String createdAt;

    public Cafe(User user, String cafeName, String address, String additionalInfo, String[] imgUrls, String createdAt) {
        this.user = user;
        this.name = cafeName;
        this.address = address;
        this.additionalInfo = additionalInfo;
        this.imgUrls = imgUrls;
        this.createdAt = createdAt;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String[] getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String[] imgUrls) {
        this.imgUrls = imgUrls;
    }

    public List<User> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<User> visitors) {
        this.visitors = visitors;
    }
}
