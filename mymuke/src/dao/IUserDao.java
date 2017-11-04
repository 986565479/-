package dao;

import po.User;
import util.Page;

public interface IUserDao {
	   //1.�����û�����ѯ
	   User query(String username);
	   //2.������û�
	   int add(User user);
	   //3.�û���¼
	   User login(String username,String password);
	   //4.��ʾ�û�
	   Page queryByName(String username,Page page);
	   //5.�޸��û�״̬
	   int updateState(int userid,int state);
	   int upuser(int userid,User user);
	   int uppassword(int userid,String password);
	   User panduan(int userid,String password);
}
