package sample.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.common.dao.entity.LoginHistory;
import sample.common.dao.mapper.LoginHistoryMapper;
import sample.common.service.LoginHistoryService;

@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {

    @Autowired
    private LoginHistoryMapper loginHistoryMapper;

    @Override
    public void insert(LoginHistory loginHistory) {
        loginHistoryMapper.insert(loginHistory);
    }

    @Override
    public List<LoginHistory> findAll() {
        return loginHistoryMapper.findAll();
    }
}