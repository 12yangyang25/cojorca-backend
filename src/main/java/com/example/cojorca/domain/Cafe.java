package com.example.cojorca.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cafe_info")
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user; //카페를 등록한 유저 정보
    @Column(name = "cafe_name")
    private String cafeName; //카페 이름
    @Column(name = "address")
    private String address; //카페 주소
    @Column(name = "naver_place_id")
    private long naverPlaceId;
    @Column(name = "lat")
    private double lat; //위도
    @Column(name = "lng")
    private double lng; //경도
    @Column(name = "tags")
    private String tags; //카페 부가 정보
    @ElementCollection
    @CollectionTable(name = "cafe_imgUrls", joinColumns = @JoinColumn(name = "cafe_id"))
    @Column(name="imgUrl")
    private List<String> imgUrls; //이미지 url
    @ManyToMany
    @JoinTable(
            name = "cafe_visitor",
            joinColumns = @JoinColumn(name = "cafe_id"),
            inverseJoinColumns = @JoinColumn(name = "visitor_id")
    )
    private List<User> visitors;
    @Column(name = "created_at")
    private String createdAt;

    public Cafe(User user, String cafeName, String address, long naverPlaceId, double lat, double lng, String tags, List<String> imgUrls, String createdAt) {
        this.user = user;
        this.cafeName = cafeName;
        this.address = address;
        this.naverPlaceId = naverPlaceId;
        this.lat = lat;
        this.lng = lng;
        this.tags = tags;
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

    public String getCafeName() {
        return cafeName;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public long getNaverPlaceId() {
        return naverPlaceId;
    }

    public void setNaverPlaceId(long naverPlaceId) {
        this.naverPlaceId = naverPlaceId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public List<User> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<User> visitors) {
        this.visitors = visitors;
    }
}
