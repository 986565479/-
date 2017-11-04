package daoimpl;

import dao.IReplyDao;
import po.Reply;
import util.DBUtil;

public class ReplyDaoImpl implements IReplyDao {

	@Override

	public int replyMsg(Reply reply) {
		System.out.println(reply.getReplyip());
		String sql="insert into reply(msgid,userid,replycontents,replyip) values(?,?,?,?)";
	    DBUtil dUtil=new DBUtil();
	    int res=0;
	    Object [] params={reply.getMsgid(),reply.getUserid(),reply.getReplycontents(),reply.getReplyip()};
	    try {
			res=dUtil.execute(sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return res;
	}

}
