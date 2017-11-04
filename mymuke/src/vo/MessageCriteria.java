package vo;

import java.sql.Timestamp;
//管理员显示帖子多条件查询类
public class MessageCriteria {
	private  int msgid;
	private  String thename;
	private String msgtopic;
	private String realname;
	private String username;
	private Timestamp msgtime;
	private int state;
	public MessageCriteria() {
		super();
	}
	
	
	public MessageCriteria(String thename, String msgtopic, String username) {
		super();
		this.thename = thename;
		this.msgtopic = msgtopic;
		this.username = username;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public void setThename(String thename) {
		this.thename = thename;
	}


	public int getMsgid() {
		return msgid;
	}
	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}
	public String getThename() {
		return thename;
	}
	
	public String getMsgtopic() {
		return msgtopic;
	}
	public void setMsgtopic(String msgtopic) {
		this.msgtopic = msgtopic;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Timestamp getMsgtime() {
		return msgtime;
	}
	public void setMsgtime(Timestamp msgtime) {
		this.msgtime = msgtime;
	}
	
}
