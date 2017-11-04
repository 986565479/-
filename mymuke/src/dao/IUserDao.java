package dao;

import po.User;
import util.Page;

public interface IUserDao {
	   //1.根据用户名查询
	   User query(String username);
	   //2.添加新用户
	   int add(User user);
	   //3.用户登录
	   User login(String username,String password);
	   //4.显示用户
	   Page queryByName(String username,Page page);
	   //5.修改用户状态
	   int updateState(int userid,int state);
	   int upuser(int userid,User user);
	   int uppassword(int userid,String password);
	   User panduan(int userid,String password);
}
