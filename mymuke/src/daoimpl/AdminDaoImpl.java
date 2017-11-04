package daoimpl;

import dao.IAdminDao;
import po.Admin;
import util.DBUtil;

public class AdminDaoImpl implements IAdminDao {

	@Override
	public Admin login(String username, String password) {
		String sql="select id, name,pwd,authority from admin where name=? and pwd=?";
		Admin admin=null;
		Object[] paramList={username,password};
		DBUtil dbutil =new DBUtil();
		try {
			
			admin=(Admin)dbutil.getObject(Admin.class, sql, paramList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin;
	}
	}


