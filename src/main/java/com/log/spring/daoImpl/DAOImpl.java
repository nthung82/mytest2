package com.log.spring.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.log.spring.dao.DAO;
import com.log.spring.entity.LogEntity;
import com.log.spring.entity.User;

@Repository
public class DAOImpl implements DAO{
	private static final Logger logger = Logger.getLogger(DAOImpl.class);
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	
	}
	
	public boolean register(String mobile, String password, String name,  String address){
		try{
		String sqlInsert = "INSERT INTO register " +
			"(name, password, address,mobile) VALUES (?, ?,?,?)";				 		
				//if(ads!=null&&ads.getId()==null)
		jdbcTemplate.update(sqlInsert, new Object[] { 
				name,password,
				address,mobile
		});
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	//@Override
	public List<LogEntity> selectAll(String name) throws Exception {
		// TODO Auto-generated method stub
		
		String	sql;
		if(name==null||"".equals(name))
			sql="SELECT * FROM loginfo WHERE enable=1 ";
		else
			sql="SELECT * FROM loginfo WHERE enable=1 and name ='"+name+"'";
		//System.err.println(sql);
		int count = 0;
		while (true){
	    List<LogEntity> list = jdbcTemplate.query(sql, new RowMapper() {
	 
	     //   @Override
	        public LogEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	LogEntity logEntity = new LogEntity();
	        	try{
	        		logEntity.setDate(rs.getString("time"));
	        		logEntity.setLat(rs.getString("lat"));
	        		logEntity.setLot(rs.getString("lot"));
	        		logEntity.setName(rs.getString("name"));
	        	}catch(Exception ex){
	        		logger.error(ex.toString());
	        	}
	        	
	            return logEntity;
	        }
	 
	    });
	 
	    return list;
		}
	}

	@Override
	public User login(String mobile, String password) {
		// TODO Auto-generated method stub

		String	sql="SELECT * FROM register where mobile='"+mobile+"' and password='"+password+"'";
		int count = 0;
		//String name="",lat="",lot="";
		while (true){
	    List<User> list = jdbcTemplate.query(sql, new RowMapper() {
	 
	     //   @Override
	        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	User user = new User();
	        	try{
	        		
	        		user.setAddress(rs.getString("address"));
	        		user.setMobile(rs.getString("mobile"));
	        		user.setPassword(rs.getString("password"));
	        		user.setName(rs.getString("name"));
	        	}catch(Exception ex){
	        		logger.error(ex.toString());
	        	}
	        	
	            return user;
	        }
	 
	    });
	 if(list==null ||list.isEmpty())
	    return null;
	 //String result="{\"success\""+":"+"true"+","+"\"mobile"+":"+;
	 return list.get(0);
		}
	}

	@Override
	public boolean sendData(String name,String lot, String lat) {
		try{
			String sqlInsert = "INSERT INTO loginfo " +
				"(name, lot, lat,enable) VALUES (?, ?,?,?)";				 		
					//if(ads!=null&&ads.getId()==null)
			jdbcTemplate.update(sqlInsert, new Object[] { 
					name,lot,
					lat,"1"
			});
			}catch(Exception ex){
				ex.printStackTrace();
				return false;
			}
			return true;
	}

}

