 package daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.ORBPackage.InconsistentTypeCode;

import dao.IMessageDao;
import util.DBUtil;
import util.Page;
import vo.MessageCriteria;
import vo.MessageInfo;
import po.Messages;
import vo.MyMsg;
import vo.ReplyInfo;

public class MessageDaoImpl implements IMessageDao{
   
	@Override
	public Page mymsg(int userid, Page page) {
		StringBuffer sBuffer = new StringBuffer();
		String sql="select m.msgid,m.msgtopic,m.msgtime,c.accessCount,c.replyCount from user u,message m,count c where m.userid=u.userid and m.msgid=c.msgid and m.userid=?";
		sBuffer.append(sql);
		
		List<Object> params = new ArrayList<Object>(); 
		DBUtil dUtil=new DBUtil();
		params.add(userid);
		Page resPage=null;
	    resPage=dUtil.getQueryPage(sBuffer.toString(),params.toArray(),page);
		return resPage;
	}
   
	@Override
	public Page queryNew(Page page) {
		String sql="select m.msgid,u.userid,m.msgtopic,u.username,m.msgtime,m.msgcontents,c.accessCount,c.replyCount,m.state from message m,user u,count c where m.userid=u.userid and c.msgid=m.msgid and m.state>0 order by m.state desc,m.msgtime desc";
		DBUtil dUtil=new DBUtil();
		Page resPage=null;
		resPage=dUtil.getQueryPage(sql, null, page);
		return resPage;
	}
	
	@Override
	public MessageInfo get(int msgid) {
		String sql="select m.msgid,m.msgtopic,t.thename,u.realname,u.sex,u.city,m.msgcontents,m.msgip,m.msgtime from message m,theme t,user u where m.userid=u.userid and m.theid=t.theid and msgid=?";
		DBUtil dUtil=new DBUtil();
		MessageInfo mInfo=null;
		Object[] param={msgid};
		try {
			mInfo=(MessageInfo)dUtil.getObject(MessageInfo.class, sql, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mInfo;
	}
	
	@Override
	public Page getReply(int msgid, Page page) {
		String sql="select r.replyid,r.msgid,u.realname,u.sex,u.city,r.replycontents,r.replyip,r.replytime from reply r,user u where  r.userid=u.userid and msgid=?";
		DBUtil dUtil=new DBUtil();
		Object[] param={msgid};
		Page resPage=null;
		resPage=dUtil.getQueryPage(sql, param, page);
		return resPage;
	}
	@Override
	
	public long queryCountByDate(Date startDate, Date endDate) {
		String sql="select count(*) from message where msgtime between ? and ?";
		DBUtil dUtil=new DBUtil();
		Object [] paramList = {startDate,endDate};
		Map<String, Object> map=null;
		try {
			map=dUtil.getObject(sql, paramList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long count=(long)map.get("count(*)");
		System.out.println(count);
		
		return count;
	}
	@Override
	public Page query(MessageCriteria criteria, Page page) {
       
		StringBuilder sql = new StringBuilder("select m.msgid,m.msgtopic,u.realname,m.msgtime,u.username,t.thename,m.state from message m,user u,theme t where m.userid=u.userid and m.theid=t.theid and 1=1 ");
        
		List<Object> params = new ArrayList<Object>();
		
		String msgtopic = criteria.getMsgtopic();
		if(msgtopic!=null&&!msgtopic.trim().isEmpty()){
			sql.append("and msgtopic like ?");
			params.add("%"+msgtopic+"%");
		}
		String username = criteria.getUsername();
		if(username!=null&&!username.trim().isEmpty()){
			sql.append("and username like ?");
			params.add("%"+username+"%");
		}
		String thename = criteria.getThename();
		if(thename!=null&&!thename.trim().isEmpty()){
			sql.append("and thename like ?");
			params.add("%"+thename+"%");
		}
		
		DBUtil dUtil=new DBUtil();
		Page resPage=null;
		resPage=dUtil.getQueryPage(sql.toString(), params.toArray(), page);
		return resPage;
	}
	@Override
	
	public int updateState(int msgid, int state) {
		String sql="update message set state=? where msgid=?";
		DBUtil dbUtil = new DBUtil();
		int res=0;
		Object [] paramList= {state,msgid};
		try {
			res=dbUtil.execute(sql, paramList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	@Override
	
	public int addMsg(Messages message) {
		String sql = "insert into message(userid,msgtopic,msgcontents,msgip,state,theid) values(?,?,?,?,?,?)";
		DBUtil dUtil=new DBUtil();
		System.out.println(message.getTheid());
		Object [] paramList = {message.getUserid(),message.getMsgtopic(),message.getMsgcontents(),message.getMsgip(),message.getState(),message.getTheid()};
	    int res=0;
	    try {
			res = dUtil.execute(sql, paramList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return res;
	}
	@Override

	public Page queryHot(Page page) {
		// TODO Auto-generated method stub
		String sql="select m.msgid,u.userid,m.msgtopic,u.username,m.msgtime,m.msgcontents,c.accessCount,c.replyCount,m.state from message m,user u,count c where m.userid=u.userid and c.msgid=m.msgid and m.state>0 order by m.state desc,c.replyCount desc,m.msgtime desc";
        DBUtil dUtil=new DBUtil();
		Page resPage=null;
		resPage=dUtil.getQueryPage(sql, null, page);
		return resPage;
	}
	@Override
	public Page queryTheme(Page page) {
		String sql="select t.thename,m.msgid,u.userid,m.msgtopic,u.username,m.msgtime,m.msgcontents,c.accessCount,c.replyCount,m.state from message m,user u,count c,theme t where m.userid=u.userid and c.msgid=m.msgid and t.theid=m.theid and m.state>0 order by m.state desc,c.replyCount desc,m.msgtime desc";
        DBUtil dUtil=new DBUtil();
		Page resPage=null;
		resPage=dUtil.getQueryPage(sql, null, page);
		return resPage;
	}

}
