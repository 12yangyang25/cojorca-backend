package com.example.cojorca.DTO;

import com.example.cojorca.domain.User;

import java.util.List;

public class CafeInfoDTO {
    private User user; //카페를 등록한 유저 정보
    private String cafeName; //카페 이름
    private String address; //카페 주소
    private String tags; //카페 부가 정보
    private List<String> imgUrls; //이미지 url
    private double lat; //위도
    private double lng; //경도
    private long naverPlaceId; //네이버 플레이스 id

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

}
