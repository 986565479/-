package serviceimpl;

import dao.IAdminDao;
import daoimpl.AdminDaoImpl;
import po.Admin;
import service.IAdminService;

public class AdminServiceImpl implements IAdminService {
    private IAdminDao IAdminDao = new AdminDaoImpl();
	@Override
	public Admin login(String username, String password) {
		// TODO Auto-generated method stub
		return IAdminDao.login(username, password);
	}

}
