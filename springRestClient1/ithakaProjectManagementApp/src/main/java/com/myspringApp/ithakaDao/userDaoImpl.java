package com.myspringApp.ithakaDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.myspringApp.ithakaModel.Users;

public class userDaoImpl implements userDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addUser(Users user) {
		
		//users table
		//System.out.println("addUser " + user.getUserName() + " " + user.getEnabled());
		String sql = "insert into users(userName, userId, password, enabled, emailId) values (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] {
			user.getUserName(),	
			user.getUserId(),
			user.getPassword(),
			user.getEnabled(),
			user.getEmailId() });
		
		//user_roles table
		String sql_role = "insert into user_roles(roleID, userName, userRole) values (?, ?, ?)";
		jdbcTemplate.update(sql_role, new Object[] {
				user.getUserId(),
				user.getUserName(),
				user.getUserRole()
		});
		
	}
	
	public void addUserRole(Users user) {
		// TODO Auto-generated method stub
		
	}

	
	


	public List<Users> getAllUsers() {
		String sql = "select * from users";
		
		List<Users> userList = jdbcTemplate.query(sql, new ResultSetExtractor<List<Users>>() {

			public List<Users> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Users> list = new ArrayList<Users>();
				
				while(rs.next()) {
					
					Users user = new Users();
					user.setUserName(rs.getString(1));
					user.setUserId(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setEnabled(rs.getInt(4));
					user.setEmailId(rs.getString(5));
					list.add(user);
				}
				return list;
			}//end of extractData method
		});//end of jdbcTemplate method
		
		return userList;
	}

	public Users getUserById(String userId) {
		String sql = "select * from users where userId = ?";
		Users user = jdbcTemplate.queryForObject(sql, new Object[] {userId},
				new RowMapper<Users>() {

			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Users user = new Users();
				
				user.setUserName(rs.getString(1));
				user.setUserId(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEnabled(rs.getInt(4));
				user.setEmailId(rs.getString(5));
				
				return user;
			}
			
			
		});
		return user;
	}
	
	public Users getUserByName(String userName) {
		String sql = "select * from users where userName = ?";
		Users user = jdbcTemplate.queryForObject(sql, new Object[] {userName}, new RowMapper<Users>() {

			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Users user = new Users();
				
				user.setUserName(rs.getString(1));
				user.setUserId(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEnabled(rs.getInt(4));
				user.setEmailId(rs.getString(5));
				
				return user;
			}
			
			
		});
		return user;
	}

	public void updateUser(Users user) {
		String sql = "Update users set userName=?, password=?, enabled=?, emailId=? where userId=?";
		
		jdbcTemplate.update(sql, new Object[] {user.getUserName(), user.getPassword(),
				user.getEnabled(), user.getEmailId(), user.getUserId()});
		
	}

	public Users getUserInfo(String userId) {
		String sql = "select users.userName, users.userId, users.password, users.emailId, user_roles.userRole from"
				+ " users JOIN user_roles ON users.userId = user_roles.roleID where users.enabled = 1 and users.userId = ?";
		
		Users userinfo = jdbcTemplate.queryForObject(sql, new Object[] {userId}, new RowMapper<Users>() {

			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				Users user = new Users	();
				user.setUserName(rs.getString(1));
				user.setUserId(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmailId(rs.getString(4));
				user.setUserRole(rs.getString(5));
				return user;
			}
		});
		
		return userinfo;
	}

	
}//end of userDemoImpl class
