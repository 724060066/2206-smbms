package com.buba.service.login;

import com.buba.dao.BaseDao;
import com.buba.dao.login.LoginDao;
import com.buba.dao.login.LoginDaoImpl;
import com.buba.pojo.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;

/**
 * @author chenrui
 * @version 1.0
 * @date 2023/3/23 13:59
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService{

    private LoginDao loginDao;

    public LoginServiceImpl() {
        loginDao = new LoginDaoImpl();
    }
    /**
     * 登录
     * @param userCode
     * @param userPassword
     * @return 0:用户不存在；1.密码错误；2.登录成功
     */
    @Override
    public User login(String userCode, String userPassword) {
        Connection connection = null;
        User user = null;
        try{
            connection = BaseDao.getConnection();
            user = loginDao.getUserByUserCode(connection, userCode);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseDao.closeResource(connection, null, null);
        }

        return user;
    }
}
