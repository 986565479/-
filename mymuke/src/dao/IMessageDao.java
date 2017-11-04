package dao;

import java.util.Date;

import util.Page;
import vo.MessageCriteria;
import vo.MessageInfo;
import po.Messages;

public interface IMessageDao {
	  //1.分页查询我的问题
		//param MyMsg 查询条件
		//param page 分页信息
		//return 查询结果
		Page mymsg(int userid,Page page);
		//2.分页查询最新五条
		Page queryNew(Page page);
		//3.message显示查询楼主信息
		MessageInfo get(int magid);
		//4.显示回复信息
		Page getReply(int msgid, Page page);
		//5.发帖统计
		long queryCountByDate(Date startDate, Date endDate);
		//6.管理员帖子搜索
		Page query(MessageCriteria criteria,Page page);
		//7.改变信息状态
		int updateState(int msgid,int state);
		//8.发布问题
		int addMsg(Messages message);
		//9.分页查询最热五条
		Page queryHot(Page page);
		//10.分页查询话题五条
		Page queryTheme(Page page);
	
}
