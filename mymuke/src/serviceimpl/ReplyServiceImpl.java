package serviceimpl;

import dao.IReplyDao;
import daoimpl.ReplyDaoImpl;
import po.Reply;
import service.IReplyService;

public class ReplyServiceImpl implements IReplyService{
    private IReplyDao idao = new ReplyDaoImpl();
	@Override
	public int replyMsg(Reply reply) {
		return idao.replyMsg(reply);
	}

}
