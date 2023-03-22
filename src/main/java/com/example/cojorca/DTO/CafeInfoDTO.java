package com.example.cojorca.DTO;

import com.example.cojorca.domain.User;

import java.util.Date;

public class CafeInfoDTO {
    private User user; //카페를 등록한 유저 정보
    private String cafeName; //카페 이름
    private String address; //카페 주소
    private String[] additionalInfo; //카페 부가 정보
    private String[] imgUrls;



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

    public String[] getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String[] additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String[] getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String[] imgUrls) {
        this.imgUrls = imgUrls;
    }

}
