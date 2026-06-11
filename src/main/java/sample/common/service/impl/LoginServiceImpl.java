package sample.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.common.dao.entity.Login;
import sample.common.dao.mapper.LoginMapper;
import sample.common.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public void insert(Login login) {
        loginMapper.insert(login);
    }

    @Override
    public Login login(Login login) {
        return loginMapper.findByUsernameAndPassword(login);
    }

    @Override
    public Login findByUsername(String username) {
        return loginMapper.findByUsername(username);
    }
}