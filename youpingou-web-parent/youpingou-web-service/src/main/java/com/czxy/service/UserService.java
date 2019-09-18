package com.czxy.service;

import com.czxy.dao.UserMapper;
import com.czxy.pojo.group.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void saveUser(User user){
        userMapper.insert(user);
    }

    public User findUserByphone(User user){
        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("mobile",user.getMobile());
        criteria.andEqualTo("name",user.getName());
        List<User> users = userMapper.selectByExample(example);
        if (users.size()!=0){
            return users.get(0);
        }
        return null;
    }

    public User findUserByMobileAndPassword(String mobile, String password) {
        Example example = new Example(User.class);
        // 1. 手机和密码都必须相等
        example.createCriteria()
                .andEqualTo("mobile",mobile)
                .andEqualTo("password",password);
        // 2. 查询
        List<User> list = userMapper.selectByExample(example);

        return list.size()>0?list.get(0):null;
    }
}