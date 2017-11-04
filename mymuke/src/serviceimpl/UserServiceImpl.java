package serviceimpl;

import dao.IUserDao;
import daoimpl.UserDaoImpl;
import po.User;
import service.IUserService;
import util.Page;

public class UserServiceImpl implements IUserService {
    private IUserDao dao=new UserDaoImpl();
	@Override
	public int UserResgister(User user) {
		User u=null;
		//int result=0;
		u=dao.query(user.getUsername());
		if(u!=null){
			return 0;
		}else{
		return dao.add(user);
		}
	}
    public boolean namevalidate(String name){
    	boolean flag=true;
    	User u=null;
    	u=dao.query(name);
    	if(u!=null){
    		flag=false;
    	}
    	return flag;
    }
	@Override
	public User login(String username, String password) {
	User user = null;
	user = dao.login(username, password);
	return user;
	}
	@Override
	public Page queryByName(String username, Page page) {
		// TODO Auto-generated method stub
		return dao.queryByName(username, page);
	}
	@Override
	public int updateState(int userid, int state) {
		// TODO Auto-generated method stub
		return dao.updateState(userid, state);
	}
	@Override
	public int upuser(int userid, User user) {
		// TODO Auto-generated method stub
		return dao.upuser(userid, user);
	}
	@Override
	public User query(String username) {
		// TODO Auto-generated method stub
		return dao.query(username);
	}
		public int uppassword(int userid,String password,String oldpassword){
			if(dao.panduan(userid, oldpassword)!=null){
				return dao.uppassword(userid, password);
			}else{
				return -1;
			}
		}
}
