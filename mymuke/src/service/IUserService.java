package service;

import po.User;
import util.Page;

public interface IUserService {
	  //1.�û�ע��
	  int UserResgister(User user);
	  //2.�û������
	  boolean namevalidate(String name);
	  //3.��¼
	  User login(String username,String password);
	  //4.��ʾ�û�
	  Page queryByName(String username,Page page);
	  //5.�޸��û�״̬
	  int updateState(int userid,int state);
	  int upuser(int userid,User user);
	  //1.�����û�����ѯ
	   User query(String username);
	  int uppassword(int userid,String password,String oldpassword);
}
