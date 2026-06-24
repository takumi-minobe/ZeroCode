package sample.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.common.dao.entity.Login;
import sample.common.dao.mapper.LoginMapper;
import sample.common.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void insert(Login login) {
        String encodedPassword = passwordEncoder.encode(login.getPassword());
        login.setPassword(encodedPassword);

        loginMapper.insert(login);
    }

    @Override
    public Login login(Login login) {

        Login dbLogin = loginMapper.findByUsername(login.getUsername());

        if (dbLogin == null) {
            return null;
        }

        if (Boolean.TRUE.equals(dbLogin.getAccountLocked())) {
            return null;
        }

        if (!passwordEncoder.matches(login.getPassword(), dbLogin.getPassword())) {

            int failedCount = dbLogin.getLoginFailedCount() + 1;
            dbLogin.setLoginFailedCount(failedCount);

            if (failedCount >= 5) {
                dbLogin.setAccountLocked(true);
            }

            loginMapper.updateLoginFailedCount(dbLogin);

            return null;
        }

        loginMapper.resetLoginFailedCount(dbLogin.getUsername());

        return dbLogin;
    }

    @Override
    public Login findByUsername(String username) {
        return loginMapper.findByUsername(username);
    }

    @Override
    public boolean changePassword(String username, String currentPassword, String newPassword) {

        Login dbLogin = loginMapper.findByUsername(username);

        if (dbLogin == null) {
            return false;
        }

        if (!passwordEncoder.matches(currentPassword, dbLogin.getPassword())) {
            return false;
        }

        String encodedNewPassword = passwordEncoder.encode(newPassword);

        Login login = new Login();
        login.setUsername(username);
        login.setPassword(encodedNewPassword);

        loginMapper.updatePassword(login);

        return true;
    }
}