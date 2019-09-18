package com.czxy.service;

import com.czxy.dao.AddressMapper;
import com.czxy.pojo.group.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 15:32 2019-01-05
 */
@Service
@Transactional
public class AddressService {

    @Autowired
    private AddressMapper addressMapper;

    public List<Address> findAddressByUserId(Integer userId){
        List<Address> address = addressMapper.findAddressByUserId(userId);
        return address;
    }

}
