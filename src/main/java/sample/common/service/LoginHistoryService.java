package sample.common.service;

import java.util.List;

import sample.common.dao.entity.LoginHistory;

public interface LoginHistoryService {

    void insert(LoginHistory loginHistory);

    List<LoginHistory> findAll();
}