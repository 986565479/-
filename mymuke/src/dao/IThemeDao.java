package dao;

import java.util.List;

import po.Theme;
import util.Page;

public interface IThemeDao {
	 //查询所有主题
	   List getAll();
	   //添加主题
	   int add(Theme theme);
	   //删除主题
	   int delete(int theid);
	   //查询主题
	   Page query(String key, Page page);
}