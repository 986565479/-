package dao;

import java.util.Date;

import util.Page;
import vo.MessageCriteria;
import vo.MessageInfo;
import po.Messages;

public interface IMessageDao {
	  //1.��ҳ��ѯ�ҵ�����
		//param MyMsg ��ѯ����
		//param page ��ҳ��Ϣ
		//return ��ѯ���
		Page mymsg(int userid,Page page);
		//2.��ҳ��ѯ��������
		Page queryNew(Page page);
		//3.message��ʾ��ѯ¥����Ϣ
		MessageInfo get(int magid);
		//4.��ʾ�ظ���Ϣ
		Page getReply(int msgid, Page page);
		//5.����ͳ��
		long queryCountByDate(Date startDate, Date endDate);
		//6.����Ա��������
		Page query(MessageCriteria criteria,Page page);
		//7.�ı���Ϣ״̬
		int updateState(int msgid,int state);
		//8.��������
		int addMsg(Messages message);
		//9.��ҳ��ѯ��������
		Page queryHot(Page page);
		//10.��ҳ��ѯ��������
		Page queryTheme(Page page);
	
}
