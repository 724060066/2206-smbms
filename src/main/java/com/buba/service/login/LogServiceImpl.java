package com.buba.service.login;

import com.buba.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2023/4/7 09:20
 */
@Service("logService")
public class LogServiceImpl implements LoginService{
    @Override
    public User login(String userCode, String userPassword) {
        return null;
    }
}
