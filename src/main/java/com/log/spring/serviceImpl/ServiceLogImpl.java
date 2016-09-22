package com.log.spring.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;







import com.log.spring.daoImpl.DAOImpl;
import com.log.spring.entity.LogEntity;
import com.log.spring.entity.User;
import com.log.spring.service.ServiceLog;

@Service
public class ServiceLogImpl implements ServiceLog {
	@Autowired
	private DAOImpl dAOImpl;
	@Override
	public List<LogEntity> selectAll(String name) throws Exception {
		// TODO Auto-generated method stub
		return dAOImpl.selectAll(name);
	}
	@Override
	public boolean register(String mobile, String password, String name,
			String address) {
		// TODO Auto-generated method stub
		return dAOImpl.register(mobile, password, name, address);
	}
	@Override
	public User login(String mobile, String password) {
		// TODO Auto-generated method stub
		return dAOImpl.login(mobile, password);
	}
	@Override
	public boolean sendData(String name, String lot, String lat) {
		// TODO Auto-generated method stub
		return dAOImpl.sendData(name, lot, lat);
	}
	

}
