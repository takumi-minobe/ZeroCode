package sample.common.dao.entity;

import java.time.LocalDateTime;

public class LoginHistory {

    private Integer id;
    private String username;
    private String loginResult;
    private String ipAddress;
    private LocalDateTime loginDatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getLoginDatetime() {
        return loginDatetime;
    }

    public void setLoginDatetime(LocalDateTime loginDatetime) {
        this.loginDatetime = loginDatetime;
    }
}