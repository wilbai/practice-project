package com.wil.service;

import com.wil.dao.UserDao;
import com.wil.entity.User;
import com.wil.exception.ServiceException;
import org.springframework.stereotype.Service;

/**
 * Created by wil on 2018/8/17.
 */
@Service
public class UserService {

    UserDao userDao = new UserDao();

    public User login(String phone, String password) throws ServiceException {
        User user = userDao.findByPhone(phone);
        if(user == null) {
            throw new ServiceException("用户不存在！");
        }
        if(user.getPassword().equals(password)) {
            return user;
        } else {
            throw new ServiceException("密码错误");
        }

    }

    public void register(String phone, String password) {

        User user = userDao.findByPhone(phone);
        if(user == null) {
            user = new User();
            user.setPhone(phone);
            //Todo 密文存储 加盐 codec md5
            user.setPassword(password);
            userDao.save(user);
        } else {
            throw new ServiceException("帐号已存在，请重新输入");
        }
    }
}
