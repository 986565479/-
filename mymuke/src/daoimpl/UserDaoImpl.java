package daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.fabric.xmlrpc.base.Params;
import com.sun.istack.internal.Builder;


import dao.IUserDao;
import po.User;
import util.DBUtil;
import util.Page;

public class UserDaoImpl implements IUserDao{
     DBUtil dbutil=new DBUtil();
	@Override
	
	
	public int upuser(int userid,User user){
		String sql = "update user set realname=?,sex=?,hobbys=?,birthday=?,city=?,email=?,qq=? where userid=?";
		Object[] params = {user.getRealname(),user.getSex(),user.getHobbys(),user.getBirthday(),user.getCity(),user.getEmail(),user.getQq(),userid };
		int count = 0;
		try {
			count = dbutil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public User query(String name) {
		String sql="select * from user where username = ?";
		User user=null;
		Object[] paramList={name};
		try {
			
			user=(User)dbutil.getObject(User.class, sql, paramList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int add(User user) {
		String sql="insert into user(username,password,realname,sex,hobbys,birthday,city,email,qq,state) values (?,?,?,?,?,?,?,?,?,?)";
		int result=0;
		Object [] paramList={user.getUsername(),user.getPassword(),user.getRealname(),user.getSex(),user.getHobbys(),user.getBirthday(),user.getCity(),user.getEmail(),user.getQq(),user.getState()};
		try {
			result=dbutil.execute(sql, paramList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public User login(String username, String password) {
		String sql="select userid,username,password,realname from user where username=? and password=?";
		User user=null;
		Object[] paramList={username,password};
		
		try {
			
			user=(User)dbutil.getObject(User.class, sql, paramList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Page queryByName(String username, Page page) {
	   System.out.println(username);
	   StringBuilder sBuilder = new StringBuilder("select * from user where 1=1 ");
	   List<Object> params = new ArrayList<Object>();
	   
	   if(username!=null&&!username.trim().isEmpty()){
		   sBuilder.append("and username like ?");
		   params.add("%"+username+"%");
	   }
		  
		Page respage  = null;
		DBUtil dUtil = new DBUtil();
		respage= dUtil.getQueryPage(sBuilder.toString(), params.toArray(), page);
		return respage;
	}

	@Override
	public int updateState(int userid, int state) {
		String sql="update user set state=? where userid=?";
		
		int res=0;
		Object [] paramList= {state,userid};
		try {
			res=dbutil.execute(sql, paramList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
		public int uppassword(int userid,String password){
			String sql = "update user set password=? where userid=?";
			Object[] params = {password,userid };
			int count = 0;
			try {
				count = dbutil.execute(sql, params);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
		
		
				public User panduan(int userid,String password){
					String sql = "select * from user where userid = ? and password=?";
					Object[] params = {userid,password };
					User returnuser = null;
					try {
						returnuser = (User) dbutil.getObject(User.class, sql, params);
					} catch (Exception e) {

						e.printStackTrace();
					}
					return returnuser;
				}
}
