package com.myspringApp.ithakaModel;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class Users{
private String userName;
private String userId;
private String password;
private int enabled;
private String emailId;
private String userRole;
private String roleID;



//public Users(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
	//	boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String userId) 
	//{
	  //super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities); 
	  
	  //this.userId = userId;
	  
	 //}
	 

public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getEnabled() {
	return enabled;
}
public void setEnabled(int enabled) {
	this.enabled = enabled;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getUserRole() {
	return userRole;
}
public void setUserRole(String userRole) {
	this.userRole = userRole;
}
public String getRoleID() {
	return roleID;
}
public void setRoleID(String roleID) {
	this.roleID = roleID;
}
	


}
