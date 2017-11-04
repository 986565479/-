package service;

import java.util.Date;

import po.Messages;
import util.Page;
import vo.MessageCriteria;
import vo.MessageInfo;

public interface IMessageService {
	 //��ҳ��ʾ�ҵ�����
	   Page MyMsg(int userid,Page page);
	   //2.��ҳ��ѯ��������
	   Page queryNew(Page page);
	   //3.message��ʾ��ѯ¥����Ϣ
	   MessageInfo get(int msgid);
	   //4.��ʾ�ظ���Ϣ
	   Page getReply(int msgid, Page page);
	   //5.����ͳ��
	   long queryCountByDate(Date startDate, Date endDate);
	   //6.����Ա��������
	 	Page serch(MessageCriteria criteria,Page page);
	   //7.�ı���Ϣ״̬
	 	int updateState(int msgid,int state);
	 	//8��������Ϣ
	 	int addMsg(Messages message);
	 	//9.��ҳ��ѯ��������
	 	Page queryHot(Page page);
	 	//10.��ҳ��ѯ��������
	 	Page queryTheme(Page page);
}
