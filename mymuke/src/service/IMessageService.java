package service;

import java.util.Date;

import po.Messages;
import util.Page;
import vo.MessageCriteria;
import vo.MessageInfo;

public interface IMessageService {
	 //分页显示我的问题
	   Page MyMsg(int userid,Page page);
	   //2.分页查询最新五条
	   Page queryNew(Page page);
	   //3.message显示查询楼主信息
	   MessageInfo get(int msgid);
	   //4.显示回复信息
	   Page getReply(int msgid, Page page);
	   //5.发帖统计
	   long queryCountByDate(Date startDate, Date endDate);
	   //6.管理员帖子搜索
	 	Page serch(MessageCriteria criteria,Page page);
	   //7.改变信息状态
	 	int updateState(int msgid,int state);
	 	//8。发布消息
	 	int addMsg(Messages message);
	 	//9.分页查询最热五条
	 	Page queryHot(Page page);
	 	//10.分页查询话题五条
	 	Page queryTheme(Page page);
}
