package com.buba.service.login;

import com.buba.pojo.User;

public interface LoginService {

    /**
     * 登录
     * @param userCode
     * @param userPassword
     * @return
     */
    User login(String userCode, String userPassword);
}
