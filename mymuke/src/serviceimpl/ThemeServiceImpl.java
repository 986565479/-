package serviceimpl;

import java.util.List;

import dao.IThemeDao;
import daoimpl.ThemeDaoImpl;
import po.Theme;
import service.IThemeService;
import util.Page;


public class ThemeServiceImpl implements IThemeService {
    private IThemeDao idao=new ThemeDaoImpl();
	@Override
	public List getAll() {
		return idao.getAll();
	}
	@Override
	public int add(Theme theme) {
		// TODO Auto-generated method stub
		return idao.add(theme);
	}
	@Override
	public int delete(int theid) {
		// TODO Auto-generated method stub
		return idao.delete(theid);
	}
	@Override
	public Page query(String key, Page page) {
		// TODO Auto-generated method stub
		
		return idao.query(key, page);
	}



}
