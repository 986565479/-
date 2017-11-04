package serviceimpl;

import java.util.Date;

import dao.IMessageDao;
import daoimpl.MessageDaoImpl;
import po.Messages;
import service.IMessageService;
import util.Page;
import vo.MessageCriteria;
import vo.MessageInfo;

public class MessageServiceImpl implements IMessageService {
    private IMessageDao idao=new MessageDaoImpl();
	@Override
	public Page MyMsg(int userid,Page page) {
		return idao.mymsg(userid, page);
	}
	@Override
	public Page queryNew(Page page) {
		return idao.queryNew(page);
	}
	@Override
	public MessageInfo get(int msgid) {
		return idao.get(msgid);
	}
	@Override
	public Page getReply(int msgid, Page page) {
		return idao.getReply(msgid, page);
	}
	@Override
	public long queryCountByDate(Date startDate, Date endDate) {
		return idao.queryCountByDate(startDate, endDate);
	}
	@Override
	public Page serch(MessageCriteria criteria, Page page) {
		return idao.query(criteria, page);
	}
	@Override
	public int updateState(int msgid, int state) {
		return idao.updateState(msgid, state);
	}
	@Override
	public int addMsg(Messages message) {
		return idao.addMsg(message);
	}
	@Override
	public Page queryHot(Page page) {
		 return  idao.queryHot(page);
	}
	@Override
	public Page queryTheme(Page page) {
		return idao.queryTheme(page);
	}

}
