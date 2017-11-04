package service;

import po.User;
import util.Page;

public interface IUserService {
	  //1.用户注册
	  int UserResgister(User user);
	  //2.用户名检测
	  boolean namevalidate(String name);
	  //3.登录
	  User login(String username,String password);
	  //4.显示用户
	  Page queryByName(String username,Page page);
	  //5.修改用户状态
	  int updateState(int userid,int state);
	  int upuser(int userid,User user);
	  //1.根据用户名查询
	   User query(String username);
	  int uppassword(int userid,String password,String oldpassword);
}
