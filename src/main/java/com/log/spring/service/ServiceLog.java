package com.log.spring.service;

import java.util.List;

import com.log.spring.entity.LogEntity;
import com.log.spring.entity.User;

public interface ServiceLog {
	 
	    public List<LogEntity> selectAll(String name) throws Exception;
	    public boolean register(String mobile, String password, String name,  String address);
	    public User login(String mobile, String password);
	    public boolean sendData(String name,String lot, String lat);
}
