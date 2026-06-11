package sample.common.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import sample.common.dao.entity.Login;

@Mapper
public interface LoginMapper {

    void insert(Login login);

    Login findByUsernameAndPassword(Login login);

    Login findByUsername(String username);
}