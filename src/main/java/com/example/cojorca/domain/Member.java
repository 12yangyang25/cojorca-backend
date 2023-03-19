//package com.example.cojorca.domain;
//
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//
//public class Member {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 알아서 생성해주는 것 => 아이덴티티
//    private Long id;
//    private String login;
//    private String name;
//    private String html_url;
//    private String email;
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getHtml_url() {
//        return html_url;
//    }
//
//    public void setHtml_url(String html_url) {
//        this.html_url = html_url;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//}
