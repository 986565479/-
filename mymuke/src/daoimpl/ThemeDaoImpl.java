package daoimpl;

import java.util.ArrayList;
import java.util.List;
import dao.IThemeDao;
import po.Theme;
import util.DBUtil;
import util.Page;

public class ThemeDaoImpl implements IThemeDao{

	@Override
	
	public List getAll() {
		String sql="select theid,thename from theme";
		DBUtil dUtil=new DBUtil();
		List list=null;
		try {
			
			list=dUtil.getQueryList(Theme.class, sql, null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int add(Theme theme) {
		String sql="insert into theme values (?,?,?)";
		int result=0;
		Object [] paramList={theme.getTheid(),theme.getThename(),theme.getCount()};
		try {
			DBUtil dUtil =new DBUtil();
			result=dUtil.execute(sql, paramList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(int theid) {
		String sql="delete from theme where theid = ?";
		int result=0;
		Object [] paramList={theid};
		try {
			DBUtil dUtil =new DBUtil();
			result=dUtil.execute(sql, paramList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Page query(String key, Page page) {
		StringBuilder sql = new StringBuilder("select * from theme where 1=1 ");
		   List<Object> params = new ArrayList<Object>();
		   
		   if(key!=null&&!key.trim().isEmpty()){
			   sql.append("and thename like ?");
			   params.add("%"+key+"%");
		   }
			  
			Page respage  = null;
			DBUtil dUtil = new DBUtil();
			respage= dUtil.getQueryPage(sql.toString(), params.toArray(), page);
			return respage;
	}

}
