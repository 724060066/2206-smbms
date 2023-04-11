package com.buba.dao.login;

import com.buba.pojo.User;

import java.sql.Connection;

public interface LoginDao {

    /**
     * 根据userCode取得用户信息
     * @param connection
     * @param userCode
     * @return
     * @throws Exception
     */
//    User getUserByUserCode(Connection connection, String userCode) throws Exception;

    /**
     * 根据userCode取得用户信息
     * @param userCode
     * @return
     * @throws Exception
     */
    User getUserByUserCode(String userCode);
}
