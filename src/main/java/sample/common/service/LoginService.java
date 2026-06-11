package sample.common.service;

import sample.common.dao.entity.Login;

public interface LoginService {

    void insert(Login login);

    Login login(Login login);

    Login findByUsername(String username);
}