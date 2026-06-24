package sample.common.service;

import sample.common.dao.entity.Login;

public interface LoginService {

    void insert(Login login);

    Login login(Login login);

    Login findByUsername(String username);
    
    boolean changePassword(String username, String currentPassword, String newPassword);
}