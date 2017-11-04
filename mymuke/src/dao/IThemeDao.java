package dao;

import java.util.List;

import po.Theme;
import util.Page;

public interface IThemeDao {
	 //��ѯ��������
	   List getAll();
	   //�������
	   int add(Theme theme);
	   //ɾ������
	   int delete(int theid);
	   //��ѯ����
	   Page query(String key, Page page);
}