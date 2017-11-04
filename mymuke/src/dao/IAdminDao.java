package dao;

import po.Admin;

public interface IAdminDao {
	
	   Admin login(String username,String password);
}
