package sample.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import sample.common.dao.entity.LoginHistory;

@Mapper
public interface LoginHistoryMapper {

    void insert(LoginHistory loginHistory);

    List<LoginHistory> findAll();
}